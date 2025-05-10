package spring_project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "Trip_Car")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TripCar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tripCarId;
    private String tripName;
    private LocalDate departureDate; // ngày khởi hành
    private LocalTime departureTime; // giờ khởi hành
    private LocalTime departureEndTime; // giờ kết thucs chuyến xe
    private String pickupPoint; // điểm đón
    private String payPonit; // điểm trả
    private Long seatNumber; // số ghế
    private Long emptySeatNumber; // số ghế trống
    private Long priceSeatNumber; // giá ghế

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "driver_id" , referencedColumnName = "driverId")
    @JsonIgnore
    @ToString.Exclude
    private Driver driver;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "coach_id" , referencedColumnName = "coachId")
    @JsonIgnore
    @ToString.Exclude
    private Coach coach;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rickshaw_id" , referencedColumnName = "rickshawId")
    @JsonIgnore
    @ToString.Exclude
    private Rickshaw rickshaw;

}
