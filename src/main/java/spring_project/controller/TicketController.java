package spring_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring_project.dto.TicketRequests;
import spring_project.entity.Ticket;
import spring_project.entity.TicketStatus;
import spring_project.service.TicketService;

import java.util.List;

@RestController
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/api/admin-ticket/get-all-tickets")
    public ResponseEntity<List<TicketRequests>> getAllTicketsForAdmin() {
        List<TicketRequests> tickets = ticketService.getAllTickets();
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/api/user-ticket/get-ticket-userid/{userId}")
    public ResponseEntity<List<TicketRequests>> getAllTicketsByUserId(@PathVariable Long userId) {
        List<TicketRequests> tickets = ticketService.getAllTicketsByUserId(userId);
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/api/user-ticket/get-details/{ticketId}")
    public ResponseEntity<TicketRequests> getTicketById(@PathVariable Long ticketId) {
        TicketRequests ticket = ticketService.getTicketById(ticketId);
        if (ticket == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ticket);
    }

    @PostMapping("/api/user-ticket/book-ticket")
    public ResponseEntity<Ticket> createTicket(@RequestBody TicketRequests ticketRequest) {
        Ticket createdTicket = ticketService.createTicket(ticketRequest);
        return ResponseEntity.ok(createdTicket);
    }

    @PutMapping("/api/admin-ticket/{ticketId}")
    public ResponseEntity<Ticket> updateTicketStatus(
            @PathVariable Long ticketId,
            @RequestParam TicketStatus status) {
        Ticket updatedTicket = ticketService.updateTicketStatus(ticketId, status);
        if (updatedTicket == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedTicket);
    }

    @DeleteMapping("/api/user-ticket/{ticketId}")
    public ResponseEntity<Ticket> deleteTicketById(@PathVariable Long ticketId) {
        Ticket ticket = ticketService.deleteTicketById(ticketId);
        if (ticket == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ticket);
    }
}
