package pl.codeconcept.e2d.e2dmasterdata.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.UserEntity;
import pl.codeconcept.e2d.e2dmasterdata.model.InstructorAndAuth;
import pl.codeconcept.e2d.e2dmasterdata.service.masterdata.InstructorService;
import pl.codeconcept.e2d.e2dmasterdata.model.Instructor;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class InstructorApiImpl extends AbstractApi implements InstructorApi {

    private final InstructorService instructorService;


    @Override
    public ResponseEntity<Instructor> createInstructor(@Valid InstructorAndAuth body) {
        UserEntity userEntity = getUserFromToken();
        return instructorService.saveInstructor(body, userEntity);
    }

    @Override
    public ResponseEntity<Void> deleteInstructor(Long id) {
        UserEntity userEntity = getUserFromToken();
        return instructorService.deleteInstructor(id, userEntity);
    }

    @Override
    public ResponseEntity<List<Instructor>> getAllInstructor() {
        UserEntity userEntity = getUserFromToken();
        return instructorService.getAllInstructor(userEntity);
    }

    @Override
    public ResponseEntity<Instructor> getInstructor(Long id) {
        UserEntity userEntity = getUserFromToken();
        return instructorService.getInstructorById(id, userEntity);
    }

    @Override
    public ResponseEntity<Instructor> updateInstructor(Long id, @Valid Instructor body) {
        UserEntity userEntity = getUserFromToken();
        return instructorService.updateInstructor(id, body, userEntity);
    }
}




