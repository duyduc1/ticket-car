package spring_project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_project.mapper.TripCarMapper;
import spring_project.repository.TripCarRepository;

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



}
