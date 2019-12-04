package pl.codeconcept.e2d.e2dmasterdata.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.UserEntity;
import pl.codeconcept.e2d.e2dmasterdata.model.Student;
import pl.codeconcept.e2d.e2dmasterdata.model.StudentAndAuth;
import pl.codeconcept.e2d.e2dmasterdata.service.masterdata.StudentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class StudentApiImpl extends AbstractApi implements StudentApi {

    private final StudentService studentService;

    @Override
    public ResponseEntity<Student> createStudent(@Valid StudentAndAuth body) {
        UserEntity userEntity = getUserFromToken();
        return studentService.saveStudent(body,userEntity);
    }

    @Override
    public ResponseEntity<Void> deleteStudent(Long id) {
        UserEntity userEntity = getUserFromToken();
        return studentService.deleteStudent(id,userEntity);
    }

    @Override
    public ResponseEntity<List<Student>> getAllStudent() {
        UserEntity userEntity = getUserFromToken();
        return studentService.getAllStudent(userEntity);
    }

    @Override
    public ResponseEntity<Student> getStudent(Long id) {
        UserEntity userEntity = getUserFromToken();
        return studentService.getStudentById(id,userEntity);

    }

    @Override
    public ResponseEntity<Student> updateStudent(Long id, @Valid Student body) {
        UserEntity userEntity = getUserFromToken();
        return studentService.updateStudent(id,body,userEntity);
    }
}
