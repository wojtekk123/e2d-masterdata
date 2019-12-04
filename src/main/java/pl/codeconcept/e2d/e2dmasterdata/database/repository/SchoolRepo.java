package pl.codeconcept.e2d.e2dmasterdata.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.SchoolEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.UserEntity;

@RepositoryDefinition(domainClass = SchoolEntity.class, idClass = Long.class)
public interface SchoolRepo extends JpaRepository<SchoolEntity,Long> {

    SchoolEntity findByUserEntity(UserEntity userEntity);

}
