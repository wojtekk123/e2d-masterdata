package pl.codeconcept.e2d.e2dmasterdata.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.UserEntity;

@RepositoryDefinition(domainClass = UserEntity.class, idClass = Long.class)
public interface UserRepo extends JpaRepository<UserEntity,Long> {

    UserEntity findByAuthId (Long id);

}
