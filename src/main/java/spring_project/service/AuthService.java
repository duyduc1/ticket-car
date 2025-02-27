package spring_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring_project.dto.UserDTO;
import spring_project.entity.User;
import spring_project.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public UserDTO signUp(UserDTO registrationRequets) {
        UserDTO resp = new UserDTO();
        try {
            User user = new User();
            user.setEmail(registrationRequets.getEmail());
            user.setPassword(passwordEncoder.encode(registrationRequets.getPassword()));
            user.setRole("USER");
            user.setName(registrationRequets.getName());
            user.setNumberphone(registrationRequets.getNumberphone());
            User userResult = userRepository.save(user);
            if (userResult != null) {
                resp.setUser(userResult);
                resp.setMessage("User saved Successfully");
                resp.setStatusCode(200);
            }
        } catch (Exception e) {
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }

    public UserDTO signIn(UserDTO signinRequest) {
        UserDTO response = new UserDTO();
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(signinRequest.getEmail(), signinRequest.getPassword()));
            var user = userRepository.findByEmail(signinRequest.getEmail())
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            var jwt = jwtUtils.generateToken(user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("24H");
            response.setId(user.getId());
            response.setEmail(user.getEmail());
            response.setName(user.getName());
            response.setNumberphone(user.getNumberphone());
            response.setRole(user.getRole());
            response.setMessage("Đăng Nhập Thành Công");
        } catch (UsernameNotFoundException e) {
            response.setStatusCode(404);
            response.setError("User not found");
        } catch (BadCredentialsException e) {
            response.setStatusCode(401);
            response.setError("Invalid credentials");
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setError(e.getMessage());
        }

        return response;
    }

    public UserDTO refreshToken(UserDTO refreshTokenRequest) {
        UserDTO response = new UserDTO();
        String ourEmail = jwtUtils.extractUsername(refreshTokenRequest.getToken());
        User users = userRepository.findByEmail(ourEmail).orElseThrow();
        if (jwtUtils.isTokenValid(refreshTokenRequest.getToken(), users)) {
            var jwt = jwtUtils.generateToken(users);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshTokenRequest.getToken());
            response.setExpirationTime("24H");
            response.setMessage("Successfully Refreshed Token");
        }
        response.setStatusCode(500);
        return response;
    }

    public void forgotPassword(String email) throws Exception {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with Email " + email));
        String token = UUID.randomUUID().toString();
        user.setResetToken(token);
        user.setTokenExpirationDate(LocalDateTime.now().plusHours(1));
        userRepository.save(user);
        String resetUrl = "http://localhost:3000/auth/reset-password?token=" + token;
        emailService.sendEmail(user.getEmail(), "Password Reset Request",
                "Click the link to reset your password: " + resetUrl);
    }

    public void resetPassword(String token, String password) throws Exception {
        User user = userRepository.findByResetToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Invailed Token"));
        if (user.getTokenExpirationDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Token has expried");
        }
        user.setPassword(passwordEncoder.encode(password));
        user.setResetToken(null);
        user.setTokenExpirationDate(null);
        userRepository.save(user);
    }
}
