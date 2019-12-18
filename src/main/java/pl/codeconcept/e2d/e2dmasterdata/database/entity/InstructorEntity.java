package pl.codeconcept.e2d.e2dmasterdata.database.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


import javax.persistence.*;
@Data
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "instructor")
public class InstructorEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "school_id")
    private SchoolEntity school;

    @NonNull
    @OneToOne (cascade=CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
}
