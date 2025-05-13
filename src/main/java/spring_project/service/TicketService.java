package spring_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_project.dto.TicketRequests;
import spring_project.entity.Ticket;
import spring_project.entity.TripCar;
import spring_project.entity.User;
import spring_project.mapper.TicketMapper;
import spring_project.repository.TickerRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketService {

    @Autowired
    private TickerRepository ticketRepository;

    @Autowired
    private TripCarService tripCarService;

    @Autowired
    private ManageUserByAdminService manageUserByAdminService;

    @Autowired
    private TicketMapper ticketMapper;

    public List<TicketRequests> getAllTicketsByUserId(Long id) {
        List<Ticket> listTickets = ticketRepository.findByUserId(id);
        return listTickets.stream()
                .map(ticketMapper::toDto)
                .collect(Collectors.toList());
    }

    public TicketRequests getTicketById(Long tickerId) {
        Ticket ticket = ticketRepository.findById(tickerId).orElse(null);
        return ticketMapper.toDto(ticket);
    }

    public Ticket createTicket(TicketRequests ticketRequests) {
        Ticket newTicket = new Ticket();
        newTicket.setSeatNumber(ticketRequests.getSeatNumber());
        User user = manageUserByAdminService.findById(ticketRequests.getId());
        TripCar tripCar = tripCarService.findTripCarById(ticketRequests.getTripCarId());
        newTicket.setUser(user);
        newTicket.setTripCar(tripCar);
        return ticketRepository.save(newTicket);
    }

    public Ticket deleteTicketById(Long ticketId) {
        Optional<Ticket> ticket = ticketRepository.findById(ticketId);
        return ticket.orElse(null);
    }
}
