package pl.codeconcept.e2d.e2dmasterdata.database.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "auth")
public class AuthEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;
}
