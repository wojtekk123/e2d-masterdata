package pl.codeconscept.e2d.e2d_masterdata.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.codeconscept.e2d.e2d_masterdata.database.entity.UserEntity;
import pl.codeconscept.e2d.e2d_masterdata.database.repository.UserRepo;
import pl.codeconscept.e2d.e2d_masterdata.service.StudentService;
import pl.codeconscept.e2d.e2dmasterdata.api.StudentApi;
import pl.codeconscept.e2d.e2d_masterdata.dto.Student;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class StudentApiImpl implements StudentApi {

    private final StudentService studentService;
    private final UserRepo userRepo;

    @Override
    public ResponseEntity<UserEntity> createStudent(@Valid Student body) {
        UserEntity userEntity = studentService.saveUser(body);
        return new ResponseEntity<>(userEntity,HttpStatus.OK) ;
    }

    @Override
    public ResponseEntity<Void> deleteStudent(Integer id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<UserEntity>> getAllStudent() {
        List<UserEntity> allStudent = studentService.getAllStudent();
        return new ResponseEntity<>(allStudent,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserEntity> getStudent(Integer id) {
        UserEntity studentById = studentService.getStudentById(id);
        return new ResponseEntity<>(studentById,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserEntity> updateStudent(Integer id, @Valid Student body) {
        UserEntity userEntity = studentService.updateStudent(id, body);
        return new ResponseEntity<>(userEntity,HttpStatus.OK);
    }
}
