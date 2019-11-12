package pl.codeconcept.e2d.e2dmasterdata.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.CarEntity;

@RepositoryDefinition(domainClass = CarEntity.class, idClass = Long.class)
public interface CarRepo extends JpaRepository<CarEntity,Long> {

}