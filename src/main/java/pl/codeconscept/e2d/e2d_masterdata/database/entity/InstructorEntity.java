package pl.codeconscept.e2d.e2d_masterdata.database.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "instructor")
public class InstructorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private SchoolEntity schoolId;

    public InstructorEntity(SchoolEntity schoolId) {
        this.schoolId = schoolId;
    }
}
