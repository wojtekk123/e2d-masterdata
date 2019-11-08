package pl.codeconscept.e2d.e2d_masterdata.database.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
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
   // @Pattern(regexp ="^\\+(?:[0-9] ?){8,10}[0-9]$",message = "wrong number")
    @Column (name = "phone_number")
    private String phoneNumber;


}
