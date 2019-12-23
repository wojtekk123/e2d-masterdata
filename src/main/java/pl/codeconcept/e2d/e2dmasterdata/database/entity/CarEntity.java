package pl.codeconcept.e2d.e2dmasterdata.database.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "car")
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @OneToOne
    @JoinColumn(name = "school_id")
    private SchoolEntity school;

    @NonNull
    private String model;

    @NonNull
    private String brand;

    @NonNull
    @Size(min = 4,max = 9,message = "Wrong car registration number.")
    @Column(name = "registration_number")
    private String registrationNumber;
}
