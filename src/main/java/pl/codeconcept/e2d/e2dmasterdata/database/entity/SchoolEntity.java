package pl.codeconcept.e2d.e2dmasterdata.database.entity;

import lombok.Data;

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