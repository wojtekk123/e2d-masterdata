package pl.codeconcept.e2d.e2dmasterdata.database.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "car")
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "school_id")
    private SchoolEntity school;

    @NotNull
    private String model;

    @NotNull
    private String brand;

    @Size(min = 4,max = 9,message = "Wrong car registration number.")
    @Column(name = "registration_number")
    private String registrationNumber;
}
