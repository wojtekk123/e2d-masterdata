package pl.codeconscept.e2d.e2d_masterdata.database.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user_")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min=2, max=20,message = "Wrong name")
    @Column(name = "first_name")
    private String firstName;
    @NotNull
    @Size(min=2, max=20,message = "Wrong second name")
    @Column (name = "second_name")
    private String secondName;
    @NotNull
    @Email(message = "Illegal email")
    @Column (name = "email")
    private String email;
    @Column (name = "phone_number")
    private String phoneNumber;
    @OneToOne
    @JoinColumn(name = "student_id")
    private StudentEntity student;
    @OneToOne
    @JoinColumn(name = "instructor_id")
    private InstructorEntity instructor;

    public UserEntity(String firstName, String secondName, String email, String phoneNumber, StudentEntity student, InstructorEntity instructor) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.student = student;
        this.instructor = instructor;
    }


}
