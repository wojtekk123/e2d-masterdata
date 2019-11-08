package pl.codeconscept.e2d.e2d_masterdata.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.codeconscept.e2d.e2d_masterdata.database.entity.SchoolEntity;
import pl.codeconscept.e2d.e2d_masterdata.database.entity.StudentEntity;
import pl.codeconscept.e2d.e2d_masterdata.database.entity.UserEntity;
import pl.codeconscept.e2d.e2d_masterdata.database.repository.SchoolRepo;
import pl.codeconscept.e2d.e2d_masterdata.database.repository.StudentRepo;
import pl.codeconscept.e2d.e2d_masterdata.database.repository.UserRepo;
import pl.codeconscept.e2d.e2d_masterdata.service.mappers.StudentMapper;
import pl.codeconscept.e2d.e2d_masterdata.service.mappers.UserMapper;
import pl.codeconscept.e2d.e2dmasterdata.model.Student;
import pl.codeconscept.e2d.e2dmasterdata.model.User;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepo studentRepo;
    private final SchoolRepo schoolRepo;
    private final UserRepo userRepo;

    @Transactional
    public Student saveStudent(Student student) {
        SchoolEntity schoolEntity = schoolRepo.findById(student.getSchoolId()).orElseThrow(IllegalArgumentException::new);
        UserEntity savedUser = userRepo.save(UserMapper.mapToEntity(student.getUser()));
        StudentEntity savedStudent = studentRepo.save(StudentMapper.mapToEntitySave(savedUser, student, schoolEntity));

        if (savedStudent == null) {
            throw new NullPointerException();
        }
        return StudentMapper.mapToModel(savedStudent);
    }

    public Student getStudentById(Long id) {
        StudentEntity studentEntity = studentRepo.findById(id).orElseThrow(IllegalArgumentException::new);
        return StudentMapper.mapToModel(studentEntity);
    }

    public List<Student> getAllStudent() {
        List<StudentEntity> all = studentRepo.findAll();
        return all.stream().map(StudentMapper::mapToModel).collect(Collectors.toList());
    }

    public void deleteStudent(Long id) {
        studentRepo.delete(studentRepo.findById(id).orElseThrow(IllegalArgumentException::new));
    }

    public Student updateStudent(Long id, Student student) {
        User userToChange = student.getUser();
        Long idUser = student.getUser().getId();

        StudentEntity studentEntity = studentRepo.findById(id).map(std -> {
            std.setUserEntity(userRepo.findById(idUser).map(user -> {
                user.setFirstName(userToChange.getFirstName());
                user.setSecondName(userToChange.getSecondName());
                user.setEmail(userToChange.getEmail());
                user.setPhoneNumber(userToChange.getPhoneNumber());
                return user;
            }).orElseThrow(IllegalArgumentException::new));
            std.setSchool(schoolRepo.findById(student.getSchoolId()).orElseThrow(IllegalArgumentException::new));
            std.setStartEducationDate(student.getStartEducation());
            std.setEndEducationDate(student.getEndEducation());
            return std;
        }).orElseThrow(IllegalArgumentException::new);

        return StudentMapper.mapToModel(studentRepo.save(studentEntity));
    }

}
