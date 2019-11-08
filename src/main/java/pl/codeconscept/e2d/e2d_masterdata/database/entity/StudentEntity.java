package pl.codeconscept.e2d.e2d_masterdata.database.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.codeconscept.e2d.e2dmasterdata.model.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Data
@Entity
@Table(name = "student")
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
    @ManyToOne
    @JoinColumn(name = "school_id")
    private SchoolEntity school;
    @NotNull
    @Column(name = "start_education_date")
    private Date startEducationDate;
    @NotNull
    @Column(name = "end_education_date")
    private Date endEducationDate;

}
