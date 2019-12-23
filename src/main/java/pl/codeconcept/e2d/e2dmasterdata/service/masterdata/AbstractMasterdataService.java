package pl.codeconcept.e2d.e2dmasterdata.service.masterdata;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.SchoolEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.StudentEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.UserEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.repository.InstructorRepo;
import pl.codeconcept.e2d.e2dmasterdata.database.repository.SchoolRepo;
import pl.codeconcept.e2d.e2dmasterdata.database.repository.StudentRepo;


@Component
@RequiredArgsConstructor
public abstract class AbstractMasterdataService {

    private final SchoolRepo schoolRepo;
    private final InstructorRepo instructorRepo;
    private final StudentRepo studentRepo;

    SchoolEntity getSchoolEntityForUser(UserEntity userEntity) {
        switch (userEntity.getType()) {
            case SCHOOL:
                return schoolRepo.findByUserEntity(userEntity);
            case INSTRUCTOR:
                return instructorRepo.findByUserEntity(userEntity).getSchool();
            case STUDENT:
                return studentRepo.findByUserEntity(userEntity).getSchool();
            default:
                return null;
        }
    }

    StudentEntity getStudentEntityForUser(UserEntity userEntity) {
        return studentRepo.findByUserEntity(userEntity);
    }

}