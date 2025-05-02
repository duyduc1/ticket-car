package spring_project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import spring_project.dto.DriverRequets;
import spring_project.service.DriverService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DriverController {
    private final DriverService driverService;

    @GetMapping("/useradmin-all")
    public ResponseEntity<List<DriverRequets>> getAllDrivers() {
        List<DriverRequets> allDrivers = driverService.getListDrivers();
        return new ResponseEntity<>(allDrivers, HttpStatus.OK);
    }

    @GetMapping("/useradmin-all/{id}")
    public ResponseEntity<DriverRequets> getDriverById(@PathVariable Long id) {
        DriverRequets driverById = driverService.getDriverById(id);
        return new ResponseEntity<>(driverById, HttpStatus.OK);
    }

    @
}
