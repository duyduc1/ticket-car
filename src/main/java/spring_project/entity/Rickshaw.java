package spring_project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table(name = "rickshaw")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rickshaw {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rickshawId;
    private String fullName;
    private Long phoneNumber;
    private Long yearOfBirth;
    private String descriptions;
    private String gender;
    private String url;
    private String publicId;

    @OneToOne(mappedBy = "rickhshaw" , cascade = CascadeType.ALL)
    @ToString.Exclude
    private TripCar tripCar;

    public TripCar getTripCar() {
        return tripCar;
    }

}
