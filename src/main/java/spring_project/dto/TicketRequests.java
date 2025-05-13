package spring_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketRequests {
    private Long tickerId;
    private String seatNumber;

    private Long tripCarId;
    private String tripName; // Tên chuyến
    private LocalDate departureDate; // ngày khởi hành
    private LocalTime departureTime; // giờ khởi hành
    private LocalTime departureEndTime; // giờ kết thucs chuyến xe
    private String pickupPoint; // điểm đón
    private String payPonit; // điểm trả

    private Long id;
    private String email;
    private Integer numberphone;
    private String username;
}
