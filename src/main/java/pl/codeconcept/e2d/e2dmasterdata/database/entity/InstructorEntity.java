package pl.codeconcept.e2d.e2dmasterdata.database.entity;

import lombok.Data;


import javax.persistence.*;
@Data
@Entity
@Table(name = "instructor")
public class InstructorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private SchoolEntity school;

    @OneToOne (cascade=CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
}
