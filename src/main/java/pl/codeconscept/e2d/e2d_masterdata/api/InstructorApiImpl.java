package pl.codeconscept.e2d.e2d_masterdata.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.codeconscept.e2d.e2d_masterdata.database.entity.UserEntity;
import pl.codeconscept.e2d.e2d_masterdata.database.repository.UserRepo;
import pl.codeconscept.e2d.e2d_masterdata.service.InstructorService;
import pl.codeconscept.e2d.e2dmasterdata.api.InstructorApi;
import pl.codeconscept.e2d.e2d_masterdata.dto.Instructor;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class InstructorApiImpl implements InstructorApi {

    private final InstructorService instructorService;
    private final UserRepo userRepo;

    @Override
    public ResponseEntity<UserEntity> createInstruktor(@Valid Instructor body) {
        UserEntity userEntity = instructorService.saveUser(body);
        return new ResponseEntity<>(userEntity, HttpStatus.OK) ;    }

    @Override
    public ResponseEntity<Void> deleteInstructor(Integer id) {
        instructorService.deleteInstructor(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<UserEntity>> getAllInstructor() {
        List<UserEntity> instructor = instructorService.getAllInstructor();
        return new ResponseEntity<>(instructor,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserEntity> getInstructor(Integer id) {
        UserEntity instructorById = instructorService.getInstructorById(id);
        return new ResponseEntity<>(instructorById,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserEntity> updateInstructor(Integer id, @Valid Instructor body) {
        UserEntity userEntity = instructorService.updateInstructor(id, body);
        return new ResponseEntity<>(userEntity,HttpStatus.OK);
    }
}
