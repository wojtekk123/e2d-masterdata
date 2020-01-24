package pl.codeconcept.e2d.e2dmasterdata.service.masterdata;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.InstructorEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.SchoolEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.StudentEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.UserEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.repository.InstructorRepo;
import pl.codeconcept.e2d.e2dmasterdata.database.repository.SchoolRepo;
import pl.codeconcept.e2d.e2dmasterdata.database.repository.StudentRepo;
import pl.codeconcept.e2d.e2dmasterdata.database.repository.UserRepo;
import pl.codeconcept.e2d.e2dmasterdata.model.User;
import pl.codeconcept.e2d.e2dmasterdata.model.UserId;

@Service
@RequiredArgsConstructor
public class UserDataService {

    private final UserRepo userRepo;
    private final StudentRepo studentRepo;
    private final InstructorRepo instructorRepo;
    private final SchoolRepo schoolRepo;

    public ResponseEntity<UserId> getStudentByAuthID(Long id) {

        UserEntity byAuthIdUser = userRepo.findByAuthId(id);
        StudentEntity byUserStudent = studentRepo.findByUserEntity(byAuthIdUser);
        UserId userId = new UserId();
        userId.setId(byUserStudent.getId());
        userId.setUserName(byAuthIdUser.getFirstName()+" "+byAuthIdUser.getSecondName());
        return new ResponseEntity<>(userId, HttpStatus.OK);
    }

    public ResponseEntity<UserId> getStudentById(Long id) {
        StudentEntity studentById = studentRepo.findById(id).orElseThrow(IllegalArgumentException::new);
        UserEntity userById = userRepo.findById(studentById.getUserEntity().getId()).orElseThrow(IllegalArgumentException::new);
        UserId userId = new UserId();
        userId.setId(userById.getId());
        userId.setUserName(userById.getFirstName()+" "+userById.getSecondName());
        return new ResponseEntity<>(userId, HttpStatus.OK);
    }

    public ResponseEntity<UserId> getInstructorByAuthId(Long id) {
        UserEntity byAuthIdUser = userRepo.findByAuthId(id);
        InstructorEntity byUserInstructor = instructorRepo.findByUserEntity(byAuthIdUser);
        UserId userId = new UserId();
        userId.setId(byUserInstructor.getId());
        userId.setUserName(byAuthIdUser.getFirstName()+" "+byAuthIdUser.getSecondName());
        userId.setEmail(byAuthIdUser.getEmail());
        return new ResponseEntity<>(userId, HttpStatus.OK);
    }

    public ResponseEntity<UserId> getInstructorById(Long id) {
        InstructorEntity instructorEntity = instructorRepo.findById(id).orElseThrow(IllegalArgumentException::new);
        UserEntity userById = userRepo.findById(instructorEntity.getUserEntity().getId()).orElseThrow(IllegalArgumentException::new);
        UserId userId = new UserId();
        userId.setId(userById.getId());
        userId.setUserName(userById.getFirstName()+" "+userById.getSecondName());
        userId.setEmail(userById.getEmail());
        userId.setSchoolId(instructorEntity.getSchool().getId());
        return new ResponseEntity<>(userId, HttpStatus.OK);
    }

    public ResponseEntity<UserId> getSchoolByAuthId(Long id) {
            UserEntity userEntity = userRepo.findByAuthId(id);
        SchoolEntity schoolEntity = schoolRepo.findByUserEntity(userEntity);
        UserId userId = new UserId();
        userId.setEmail(userEntity.getEmail());
        userId.userName(userEntity.getFirstName()+" "+ userEntity.getSecondName());
        userId.setSchoolId(schoolEntity.getId());

        return new ResponseEntity<>(userId,HttpStatus.OK);
    }





}
