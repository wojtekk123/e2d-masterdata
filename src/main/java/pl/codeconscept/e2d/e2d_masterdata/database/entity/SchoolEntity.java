package pl.codeconscept.e2d.e2d_masterdata.database.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
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

    public SchoolEntity(String name, String officialName) {
        this.name = name;
        this.officialName = officialName;
    }
}
