package pl.codeconcept.e2d.e2dmasterdata.api;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.InstructorEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.StudentEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.UserEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.repository.InstructorRepo;
import pl.codeconcept.e2d.e2dmasterdata.database.repository.StudentRepo;
import pl.codeconcept.e2d.e2dmasterdata.database.repository.UserRepo;
import pl.codeconcept.e2d.e2dmasterdata.model.UserId;

@RestController
@RequiredArgsConstructor
public class UserController implements RideApi {

    private final UserRepo userRepo;
    private final InstructorRepo instructorRepo;
    private final StudentRepo studentRepo;

    @Override
    public ResponseEntity<UserId> getInstructorId(Long id) {

        UserEntity byAuthIdUser = userRepo.findByAuthId(id);
        InstructorEntity byUserInstructor = instructorRepo.findByUserEntity(byAuthIdUser);
        UserId userId = new UserId();
        userId.setId(byUserInstructor.getId());
        return new ResponseEntity<>(userId, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserId> getStudentId(Long id) {

        UserEntity byAuthIdUser = userRepo.findByAuthId(id);
        StudentEntity byUserStudent = studentRepo.findByUserEntity(byAuthIdUser);
        UserId userId = new UserId();
        userId.setId(byUserStudent.getId());
        return new ResponseEntity<>(userId, HttpStatus.OK);
    }
}
