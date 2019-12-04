package pl.codeconcept.e2d.e2dmasterdata.database.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@Table(name = "student")
public class StudentEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne (cascade=CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @ManyToOne (cascade=CascadeType.PERSIST)
    @JoinColumn(name = "school_id")
    private SchoolEntity school;

    @NotNull
    @Column(name = "start_education_date")
    private Date startEducationDate;

    @NotNull
    @Column(name = "end_education_date")
    private Date endEducationDate;
}