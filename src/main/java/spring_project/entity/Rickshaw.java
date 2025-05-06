package spring_project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
