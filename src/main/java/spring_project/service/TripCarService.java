package spring_project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_project.dto.TripCarRequets;
import spring_project.entity.TripCar;
import spring_project.mapper.TripCarMapper;
import spring_project.repository.TripCarRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TripCarService {

    @Autowired
    private TripCarRepository tripCarRepository;

    @Autowired
    private DriverService driverService;

    @Autowired
    private CoachSevice coachSevice;

    @Autowired
    private RickShawService rickShawService;

    @Autowired
    private TripCarMapper tripCarMapper;

    public List<TripCarRequets> getAllTripCars() {
        List<TripCar> ListTripCars = tripCarRepository.findAll();
        return ListTripCars.stream()
                .map(tripCarMapper::toDTO)
                .collect(Collectors.toList());
    }

    public TripCarRequets getTripCarById(Long tripCarId) {
        TripCar tripCar = tripCarRepository.findById(tripCarId).orElse(null);
        return tripCarMapper.toDTO(tripCar);
    }

    public
}
