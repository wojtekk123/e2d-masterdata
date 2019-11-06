package pl.codeconscept.e2d.e2d_masterdata.database.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "student")
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "school_id")
    private SchoolEntity school;
    @NotNull
    @Column(name = "start_education_date")
    private Date startEducationDate;
    @NotNull
    @Column(name = "end_education_date")
    private Date endEducationDate;

    public StudentEntity(SchoolEntity school, Date startEducationDate, Date endEducationDate) {
        this.school = school;
        this.startEducationDate = startEducationDate;
        this.endEducationDate = endEducationDate;
    }
}
