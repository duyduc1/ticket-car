package spring_project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import spring_project.dto.RickShawRequets;
import spring_project.service.RickShawService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class RickShawController {
    private final RickShawService rickShawService;

    @GetMapping("/useradmin-all-rickshaw")
    public ResponseEntity<List<RickShawRequets>> getAllRickShaws() {
        List<RickShawRequets> allRickShaws = rickShawService.getAllRickShaws();
        return new ResponseEntity<>(allRickShaws, HttpStatus.OK);
    }

    @GetMapping("/useradmin-all-rickshaw/{rickshawId}")
    public ResponseEntity<RickShawRequets> getRickShawById(@PathVariable Long rickshawId) {
        RickShawRequets rickShawsById = rickShawService.getRickShawById(rickshawId);
        return new ResponseEntity<>(rickShawsById, HttpStatus.OK);
    }

    @PostMapping("/api-rickshaw/create-rickshaw")
    public ResponseEntity<Map<String,Object>> createRickShaw(
            @RequestParam(value = "image" , required = false) MultipartFile file,
            @RequestParam(value = "rickShawfullName", required = false) String rickShawfullName,
            @RequestParam(value = "rickShawphoneNumber" , required = false) Long rickShawphoneNumber,
            @RequestParam(value = "rickShawyearOfBirth" , required = false) Long rickShawyearOfBirth,
            @RequestParam(value = "rickShawdescriptions" , required = false) String rickShawdescriptions,
            @RequestParam(value = "rickShawgender" , required = false) String rickShawgender) {
        try {
            RickShawRequets rickShawRequets = new RickShawRequets(rickShawfullName , rickShawphoneNumber , rickShawyearOfBirth , rickShawdescriptions , rickShawgender);
            Map<String , Object> dataRickShaws = rickShawService.createRickShaw(file , rickShawRequets);
            return new ResponseEntity<>(dataRickShaws, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/api-rickshaw/update-rickshaw/{rickshawId}")
    public ResponseEntity<Map<String,Object>> updateRickShaw(
            @PathVariable Long rickshawId,
            @RequestParam(value = "image" , required = false) MultipartFile file,
            @RequestParam(value = "rickShawfullName" , required = false) String rickShawfullName,
            @RequestParam(value = "rickShawphoneNumber" , required = false) Long rickShawphoneNumber,
            @RequestParam(value = "rickShawyearOfBirth" , required = false) Long rickShawyearOfBirth,
            @RequestParam(value = "rickShawdescriptions" , required = false) String rickShawdescriptions,
            @RequestParam(value = "rickShawgender" , required = false) String rickShawgender){
        try {
            RickShawRequets rickShawRequets = new RickShawRequets(rickShawfullName , rickShawphoneNumber , rickShawyearOfBirth , rickShawdescriptions , rickShawgender);
            rickShawService.updateRickShaw(rickshawId , file , rickShawRequets);
            return new ResponseEntity<>(Map.of("message", "Driver updated susscessfully"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/api-rickshaw/delete-rickshaw/{rickshawId}")
    public ResponseEntity<Map<String,Object>> deleteRickShaw(@PathVariable Long rickshawId) {
        try {
            rickShawService.deleteRickShaw(rickshawId);
            return new ResponseEntity<>(Map.of("message", "Delete RickShaw successfully"), HttpStatus.OK);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
