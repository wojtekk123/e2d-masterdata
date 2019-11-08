package pl.codeconscept.e2d.e2d_masterdata.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.codeconscept.e2d.e2d_masterdata.service.SchoolService;
import pl.codeconscept.e2d.e2d_masterdata.service.StudentService;
import pl.codeconscept.e2d.e2dmasterdata.api.StudentApi;
import pl.codeconscept.e2d.e2dmasterdata.model.Student;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class StudentApiImpl implements StudentApi {

    private final StudentService studentService;

    @Override
    public ResponseEntity<Student> createStudent(@Valid Student body) {
        return new ResponseEntity<>(studentService.saveStudent(body),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteStudent(Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<Student>> getAllStudent() {
        return new ResponseEntity<>(studentService.getAllStudent(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Student> getStudent(Long id) {
        return new ResponseEntity<>(studentService.getStudentById(id),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Student> updateStudent(Long id, @Valid Student body) {
        return new ResponseEntity<>(studentService.updateStudent(id, body),HttpStatus.OK);
    }
}
