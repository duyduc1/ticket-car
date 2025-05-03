package spring_project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import spring_project.dto.CoachRequets;
import spring_project.service.CoachSevice;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class CoachController {

    private final CoachSevice coachSevice;

    @GetMapping("/useradmin-all-coach")
    public ResponseEntity<List<CoachRequets>> getAllCoach() {
        List<CoachRequets> allCoachs = coachSevice.getAllCoaches();
        return new ResponseEntity<>(allCoachs, HttpStatus.OK);
    }

    @GetMapping("/useradmin-all-coach/{coachId}")
    public ResponseEntity<CoachRequets> getCoachById(@PathVariable Long coachId) {
        CoachRequets coachById = coachSevice.getCoacheById(coachId);
        return new ResponseEntity<>(coachById, HttpStatus.OK);
    }

    @PostMapping("/api-coach/create-coach")
    public ResponseEntity<Map<String , Object>> createCoach(
            @RequestParam(value = "image" , required = false) MultipartFile file,
            @RequestParam(value = "coachName" , required = false) String coachName,
            @RequestParam(value = "licensePlateNumberCoach" , required = false) Long licensePlateNumberCoach) {
        try {
            CoachRequets coachRequets = new CoachRequets(coachName , licensePlateNumberCoach);
            Map<String,Object> dataCoach = coachSevice.createCoach(file , coachRequets);
            return new ResponseEntity<>(dataCoach, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error" , e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }


}
