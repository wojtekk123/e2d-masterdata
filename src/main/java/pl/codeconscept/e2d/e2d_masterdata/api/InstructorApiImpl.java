package pl.codeconscept.e2d.e2d_masterdata.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.codeconscept.e2d.e2d_masterdata.service.InstructorService;
import pl.codeconscept.e2d.e2dmasterdata.api.InstructorApi;
import pl.codeconscept.e2d.e2dmasterdata.model.Instructor;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class InstructorApiImpl implements InstructorApi {

    private final InstructorService instructorService;

    @Override
    public ResponseEntity<Instructor> createInstruktor(@Valid Instructor body) {
        return new ResponseEntity<>(instructorService.saveInstructor(body), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteInstructor(Long id) {
        instructorService.deleteInstructor(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<Instructor>> getAllInstructor() {
        return new ResponseEntity<>(instructorService.getAllInstructor(),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Instructor> getInstructor(Long id) {
        return new ResponseEntity<>(instructorService.getInstructorById(id),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Instructor> updateInstructor(Long id, @Valid Instructor body) {
        return new ResponseEntity<>(instructorService.updateInstructor(id,body),HttpStatus.OK);
    }
}




