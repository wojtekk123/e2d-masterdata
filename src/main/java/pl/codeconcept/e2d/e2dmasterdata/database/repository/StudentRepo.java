package pl.codeconcept.e2d.e2dmasterdata.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.StudentEntity;

@RepositoryDefinition(domainClass = StudentEntity.class, idClass = Long.class)
public interface StudentRepo extends JpaRepository<StudentEntity,Long> {
}