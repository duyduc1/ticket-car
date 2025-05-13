package spring_project.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring_project.dto.TripCarRequets;
import spring_project.entity.TripCar;
import spring_project.service.TripCarService;

import java.util.List;

@RestController
public class TripCarController {

    @Autowired
    private TripCarService tripCarService;

    @GetMapping("/useradmin-all-tripcar")
    public ResponseEntity<List<TripCarRequets>> getAllTripCar() {
        List<TripCarRequets> listTripCars = tripCarService.getAllTripCars();
        return new ResponseEntity<>(listTripCars, HttpStatus.OK);
    }

    @GetMapping("/useradmin-all-tripcar/{tripCarId}")
    public ResponseEntity<TripCarRequets> getTripCarById(@PathVariable Long tripCarId) {
        TripCarRequets tripCarById = tripCarService.getTripCarById(tripCarId);
        return new ResponseEntity<>(tripCarById, HttpStatus.OK);
    }

    @PostMapping("/api-tripcar/create-tripcar")
    public ResponseEntity<TripCarRequets> createTripCarByAdmin(@RequestBody TripCarRequets tripCarRequets) {
        TripCar tripCar = tripCarService.createTripCar(tripCarRequets);
        return new ResponseEntity<>(tripCarRequets, HttpStatus.OK);
    }

    @PutMapping("/api-tripcar/update-tripcar/{tripCarId}")
    public ResponseEntity<TripCarRequets> updateTripCarByAdmin(@PathVariable Long tripCarId, @RequestBody TripCarRequets tripCarRequets) {
        TripCar tripCar = tripCarService.updateTripCar(tripCarId, tripCarRequets);
        return new ResponseEntity<>(tripCarRequets, HttpStatus.OK);
    }

    @DeleteMapping("/api-tripcar/{tripCarId}")
    public ResponseEntity<String> deleteTripCarByAdmin(@PathVariable Long tripCarId) {
        tripCarService.deleteTripCar(tripCarId);
        return new ResponseEntity<>("Trip Car deleted", HttpStatus.OK);
    }
}
