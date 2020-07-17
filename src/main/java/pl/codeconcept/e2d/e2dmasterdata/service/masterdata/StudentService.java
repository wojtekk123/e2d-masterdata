package pl.codeconcept.e2d.e2dmasterdata.service.masterdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.SchoolEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.StudentEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.UserEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.enums.UserType;
import pl.codeconcept.e2d.e2dmasterdata.database.repository.InstructorRepo;
import pl.codeconcept.e2d.e2dmasterdata.database.repository.SchoolRepo;
import pl.codeconcept.e2d.e2dmasterdata.database.repository.StudentRepo;
import pl.codeconcept.e2d.e2dmasterdata.exception.E2DAccessDenied;
import pl.codeconcept.e2d.e2dmasterdata.exception.E2DExistException;
import pl.codeconcept.e2d.e2dmasterdata.exception.E2DMissingException;
import pl.codeconcept.e2d.e2dmasterdata.model.AuthBack;
import pl.codeconcept.e2d.e2dmasterdata.model.Student;
import pl.codeconcept.e2d.e2dmasterdata.model.StudentAndAuth;
import pl.codeconcept.e2d.e2dmasterdata.service.jwt.JwtAuthFilter;
import pl.codeconcept.e2d.e2dmasterdata.service.mappers.StudentMapper;
import pl.codeconcept.e2d.e2dmasterdata.service.template.TemplateRestQueries;

import javax.validation.ValidationException;
import java.rmi.ConnectIOException;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service

public class StudentService extends AbstractMasterdataService {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private SchoolRepo schoolRepo;

    @Autowired
    private TemplateRestQueries templateRestQueries;

    @Autowired
    JwtAuthFilter jwtAuthFilter;

    public StudentService(SchoolRepo schoolRepo, InstructorRepo instructorRepo, StudentRepo studentRepo) {
        super(schoolRepo, instructorRepo, studentRepo);
    }

    @Transactional
    public ResponseEntity<Student> saveStudent(StudentAndAuth studentAndAuth, UserEntity userEntity) {

        try {
            SchoolEntity schoolEntity = schoolRepo.findById(studentAndAuth.getStudent().getSchoolId()).orElseThrow(() -> new E2DMissingException("id-" + studentAndAuth.getStudent().getSchoolId()));

            if (!userEntity.getType().equals(UserType.ADMIN)) {
                SchoolEntity schoolEntityForUser = getSchoolEntityForUser(userEntity);

                if (!(userEntity.getType().equals(UserType.SCHOOL)) || !(schoolEntityForUser.getId().equals(studentAndAuth.getStudent().getSchoolId()))) {
                    throw new RuntimeException();
                }
            }
            AuthBack userData = templateRestQueries.getUserData(jwtAuthFilter.getToken(), studentAndAuth.getAuth(), UserType.STUDENT);
            StudentEntity studentEntity = StudentMapper.mapToEntity(studentAndAuth.getStudent(), schoolEntity, UserType.STUDENT, userData.getIdAuth());
            studentRepo.save(studentEntity);
            return new ResponseEntity<>(StudentMapper.mapToModel(studentEntity), HttpStatus.OK);

        } catch (ConnectIOException e) {
            throw new E2DExistException("Connection problem");
        } catch (DataAccessException | ValidationException e) {
            throw new E2DExistException("Wrong number or email");
        } catch (InvalidParameterException e) {
            throw new E2DMissingException("user already exist :" + studentAndAuth.getAuth().getUsername());
        } catch (IllegalArgumentException e) {
            throw new E2DMissingException("id- " + userEntity.getId());
        } catch (RuntimeException e) {
            throw new E2DAccessDenied("school-id:" + studentAndAuth.getStudent().getId());
        }
    }

    public ResponseEntity<Student> getStudentById(Long id, UserEntity userEntity) {

        try {
            StudentEntity studentEntity = studentRepo.findById(id).orElseThrow(() -> new E2DMissingException("id-" + id));

            if (!userEntity.getType().equals(UserType.ADMIN)) {
                SchoolEntity schoolFromUser = getSchoolEntityForUser(userEntity);

                if (!(userEntity.getType().equals(UserType.SCHOOL)) || !(schoolFromUser.getId().equals(studentEntity.getId()))) {
                    StudentEntity studentFromUser = getStudentEntityForUser(userEntity);

                    if (!(userEntity.getType().equals(UserType.STUDENT)) || !(studentFromUser.getId().equals(studentEntity.getId()))) {
                        throw new RuntimeException();
                    }
                }
            }
            return new ResponseEntity<>(StudentMapper.mapToModel(studentEntity), HttpStatus.OK);

        } catch (IllegalArgumentException e) {
            throw new E2DMissingException("id- " + id);
        } catch (RuntimeException e) {
            throw new E2DAccessDenied("get-id:" + id);
        }
    }


    public ResponseEntity<List<Student>> getAllStudent(UserEntity userEntity) {

        try {
            StudentEntity studentFromUser = getStudentEntityForUser(userEntity);
            SchoolEntity schoolFromUser = getSchoolEntityForUser(userEntity);
            List<StudentEntity> all = studentRepo.findAll();

            if (userEntity.getType().equals(UserType.ADMIN)) {
                List<Student> allStudent = all.stream().map(StudentMapper::mapToModel).collect(Collectors.toList());
                return new ResponseEntity<>(allStudent, HttpStatus.OK);

            } else if (userEntity.getType().equals(UserType.SCHOOL)||(userEntity.getType().equals(UserType.INSTRUCTOR))) {
                Stream<StudentEntity> studentEntityStream = all.stream().filter(e -> e.getSchool().getId().equals(schoolFromUser.getId()));
                List<Student> collectStudent = studentEntityStream.map(StudentMapper::mapToModel).collect(Collectors.toList());
                return new ResponseEntity<>(collectStudent, HttpStatus.OK);

            } else if (userEntity.getType().equals(UserType.STUDENT) && studentFromUser != null) {
                Stream<StudentEntity> studentEntityStream = all.stream().filter(e -> e.getSchool().getId().equals(studentFromUser.getSchool().getId()));
                List<Student> collectStudent = studentEntityStream.map(StudentMapper::mapToModel).collect(Collectors.toList());
                return new ResponseEntity<>(collectStudent, HttpStatus.OK);
            }
            return ResponseEntity.badRequest().build();

        } catch (IllegalArgumentException e) {
            throw new E2DMissingException("id: " + userEntity.getId());
        } catch (RuntimeException e) {
            throw new E2DAccessDenied("get all:");
        }
    }


    @Transactional
    public ResponseEntity<Void> deleteStudent(Long id, UserEntity userEntity) {

        try {
            StudentEntity studentEntity = studentRepo.findById(id).orElseThrow(() -> new E2DMissingException("id-" + id));

            if (!userEntity.getType().equals(UserType.ADMIN)) {
                SchoolEntity schoolEntityForUser = getSchoolEntityForUser(userEntity);

                if (!(userEntity.getType().equals(UserType.SCHOOL)) || !(schoolEntityForUser.getId().equals(studentEntity.getSchool().getId()))) {
                    throw new RuntimeException();
                }
            }
            studentRepo.delete(studentEntity);
            return ResponseEntity.ok().build();

        } catch (IllegalArgumentException e) {
            throw new E2DMissingException("id- " + id);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new E2DAccessDenied("get-id:" + id);
        }
    }


    @Transactional
    public ResponseEntity<Student> updateStudent(Long id, Student student, UserEntity userEntity) {

        try {
            StudentEntity studentEntity = studentRepo.findById(id).orElseThrow(() -> new E2DMissingException("id-" + student.getId()));
            SchoolEntity schoolEntity = schoolRepo.findById(student.getSchoolId()).orElseThrow(() -> new E2DMissingException("id-" + id));

            if (!userEntity.getType().equals(UserType.ADMIN)) {
                SchoolEntity schoolEntityForUser = getSchoolEntityForUser(userEntity);



                if (studentEntity == null || !(schoolEntityForUser.getId().equals(schoolEntity.getId())) || (!(userEntity.getType().equals(UserType.SCHOOL)||!(userEntity.getType().equals(UserType.SCHOOL))))) {
                    throw new RuntimeException();
                }
            }
            StudentMapper.mapToExistingEntity(studentEntity, student, schoolEntity);
            studentRepo.save(studentEntity);
            return new ResponseEntity<>(StudentMapper.mapToModel(studentEntity), HttpStatus.OK);

        } catch (IllegalArgumentException e) {
            throw new E2DMissingException("id- " + id);
        } catch (RuntimeException e) {
            throw new E2DAccessDenied("update-id:" + id);
        }

    }
}