package spring_project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_project.dto.TripCarRequets;
import spring_project.entity.Coach;
import spring_project.entity.Driver;
import spring_project.entity.Rickshaw;
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

    public TripCar createTripCar(TripCarRequets tripCarRequets) {
        TripCar newTripCar = new TripCar();
        newTripCar.setTripName(tripCarRequets.getTripName());
        newTripCar.setEmptySeatNumber(tripCarRequets.getEmptySeatNumber());
        newTripCar.setPriceSeatNumber(tripCarRequets.getPriceSeatNumber());
        newTripCar.setSeatNumber(tripCarRequets.getSeatNumber());
        newTripCar.setPayPonit(tripCarRequets.getPayPonit());
        newTripCar.setPickupPoint(tripCarRequets.getPickupPoint());
        newTripCar.setDepartureTime(tripCarRequets.getDepartureTime());
        newTripCar.setDepartureEndTime(tripCarRequets.getDepartureEndTime());
        newTripCar.setDepartureDate(tripCarRequets.getDepartureDate());
        Driver driver = driverService.findDriverById(tripCarRequets.getDriverId());
        Coach coach = coachSevice.findCoachById(tripCarRequets.getCoachId());
        Rickshaw rickshaw = rickShawService.getRickShaw(tripCarRequets.getRickshawId());
        newTripCar.setDriver(driver);
        newTripCar.setCoach(coach);
        newTripCar.setRickshaw(rickshaw);
        return tripCarRepository.save(newTripCar);
    }

    public TripCar updateTripCar(Long tripCarId, TripCarRequets tripCarRequets) {
        TripCar tripCar = tripCarRepository.findById(tripCarId).orElse(null);
        tripCar.setTripName(tripCarRequets.getTripName());
        tripCar.setEmptySeatNumber(tripCarRequets.getEmptySeatNumber());
        tripCar.setPriceSeatNumber(tripCarRequets.getPriceSeatNumber());
        tripCar.setSeatNumber(tripCarRequets.getSeatNumber());
        tripCar.setPayPonit(tripCarRequets.getPayPonit());
        tripCar.setPickupPoint(tripCarRequets.getPickupPoint());
        tripCar.setDepartureTime(tripCarRequets.getDepartureTime());
        tripCar.setDepartureEndTime(tripCarRequets.getDepartureEndTime());
        tripCar.setDepartureDate(tripCarRequets.getDepartureDate());
        return tripCarRepository.save(tripCar);
    }

    public boolean deleteTripCar(Long tripCarId) {
        TripCar tripCar = tripCarRepository.findById(tripCarId).orElse(null);
        tripCarRepository.delete(tripCar);
        return true;
    }
}
