package pl.codeconscept.e2d.e2d_masterdata.service;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.codeconscept.e2d.e2d_masterdata.database.entity.SchoolEntity;
import pl.codeconscept.e2d.e2d_masterdata.database.entity.StudentEntity;
import pl.codeconscept.e2d.e2d_masterdata.database.entity.UserEntity;
import pl.codeconscept.e2d.e2d_masterdata.database.repository.SchoolRepo;
import pl.codeconscept.e2d.e2d_masterdata.database.repository.StudentRepo;
import pl.codeconscept.e2d.e2d_masterdata.database.repository.UserRepo;
import pl.codeconscept.e2d.e2d_masterdata.dto.Student;
import pl.codeconscept.e2d.e2d_masterdata.dto.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepo studentRepo;
    private final SchoolRepo schoolRepo;
    private final UserRepo userRepo;
    private final JdbcTemplate jdbcTemplate;


    public UserEntity saveUser(Student studentDto) {
        StudentEntity student = saveStudent(studentDto);
        User userDto = studentDto.getUserDto();
        UserEntity user = new UserEntity(userDto.getFirstName(), userDto.getSecondName(), userDto.getEmail(), userDto.getPhoneNumber(), student, null);
        return userRepo.save(user);
    }

    public UserEntity getStudentById(Integer id) {
        return userRepo.findByStudentId(id.longValue());
    }

    public List<UserEntity> getAllStudent() {
        return userRepo.findAll().stream().filter(e -> (e.getInstructor() == null)).collect(Collectors.toList());
    }

    public void deleteStudent(Integer id) {
        userRepo.delete(userRepo.findByStudentId(id.longValue()));
    }

    public UserEntity updateStudent(Integer id, Student studentDto) {
        User userDto = studentDto.getUserDto();
        StudentEntity mapStudent = mapObject(studentDto);
        Optional<UserEntity> userOptional = Optional.of(userRepo.findByStudentId(id.longValue()));
        Long idStd = userOptional.get().getStudent().getId();
        return userOptional.map(user -> {
            user.setFirstName(userDto.getFirstName());
            user.setSecondName(userDto.getSecondName());
            user.setEmail(userDto.getEmail());
            user.setPhoneNumber(userDto.getEmail());
            user.setStudent(studentRepo.findById(idStd).map(std -> {
                std.setSchool(mapStudent.getSchool());
                std.setStartEducationDate(mapStudent.getStartEducationDate());
                std.setEndEducationDate(mapStudent.getEndEducationDate());
                return studentRepo.save(std);
            }).get());
            user.setInstructor(null);
            return userRepo.save(user);
        }).get();
    }

    public StudentEntity saveStudent(Student studentDto) {
        SchoolEntity school = schoolRepo.findById(studentDto.getSchoolId()).get();
        StudentEntity student = new StudentEntity(school, studentDto.getStartEducationDate(), studentDto.getStartEducationDate());
        return studentRepo.save(student);
    }

    private StudentEntity mapObject(Student studentDto) {
        SchoolEntity schoolEntity = schoolRepo.findById(studentDto.getSchoolId()).get();
        return new StudentEntity(schoolEntity, studentDto.getStartEducationDate(), studentDto.getEndEducationDate());
    }
}
