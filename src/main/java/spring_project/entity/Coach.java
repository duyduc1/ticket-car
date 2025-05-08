package spring_project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@Table(name = "coach")
@AllArgsConstructor
@NoArgsConstructor
public class Coach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long coachId;
    private Long licensePlateNumberCoach;
    private String coachName;
    private String url;
    private String publicId;

    @OneToOne(mappedBy = "coach" , cascade = CascadeType.ALL)
    @ToString.Exclude
    private TripCar tripCar;

    public TripCar getTripCar() {
        return tripCar;
    }
}
