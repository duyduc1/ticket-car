package spring_project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String
}
