package pl.codeconcept.e2d.e2dmasterdata.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.InstructorEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.UserEntity;

import java.util.Optional;

@RepositoryDefinition(domainClass = InstructorEntity.class, idClass = Long.class)
public interface InstructorRepo extends JpaRepository<InstructorEntity,Long> {
    InstructorEntity findByUserEntity(UserEntity userEntity);
    Optional<InstructorEntity> findById (Long id);
}