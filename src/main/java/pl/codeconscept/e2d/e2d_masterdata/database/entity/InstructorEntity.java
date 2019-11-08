package pl.codeconscept.e2d.e2d_masterdata.database.entity;

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
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;


}
