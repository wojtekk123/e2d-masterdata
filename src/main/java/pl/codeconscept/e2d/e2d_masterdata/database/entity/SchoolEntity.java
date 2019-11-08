package pl.codeconscept.e2d.e2d_masterdata.database.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Data
@Entity
@Table(name = "school")
public class SchoolEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "official_name")
    private String officialName;

}
