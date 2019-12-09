package pl.codeconcept.e2d.e2dmasterdata.service.masterdata;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.InstructorEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.SchoolEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.StudentEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.UserEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.enums.UserType;
import pl.codeconcept.e2d.e2dmasterdata.database.repository.InstructorRepo;
import pl.codeconcept.e2d.e2dmasterdata.database.repository.SchoolRepo;
import pl.codeconcept.e2d.e2dmasterdata.database.repository.StudentRepo;
import pl.codeconcept.e2d.e2dmasterdata.model.Auth;
import pl.codeconcept.e2d.e2dmasterdata.model.AuthBack;

import java.security.InvalidParameterException;

@Component
@RequiredArgsConstructor
public abstract class AbstractMasterdataService {

    private final SchoolRepo schoolRepo;

    private final InstructorRepo instructorRepo;

    private final StudentRepo studentRepo;

    @Autowired
    private RestTemplate restTemplate;

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

    protected ResponseEntity<AuthBack> setUserInAuth(Auth auth, UserType userType) {

        final String uri = "http://localhost:8081/save";
        auth.setRole(userType.toString());
        ResponseEntity<AuthBack> authResponseEntity = restTemplate.postForEntity(uri, auth, AuthBack.class);
        try {
            authResponseEntity.getBody().getIdAuth();
            return authResponseEntity;
        } catch (NullPointerException e) {
            throw new InvalidParameterException();
        }
    }
}