package pl.codeconcept.e2d.e2dmasterdata.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.SchoolEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.StudentEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.repository.SchoolRepo;
import pl.codeconcept.e2d.e2dmasterdata.database.repository.StudentRepo;
import pl.codeconcept.e2d.e2dmasterdata.exception.E2DMissingException;
import pl.codeconcept.e2d.e2dmasterdata.service.mappers.StudentMapper;
import pl.codeconcept.e2d.e2dmasterdata.model.Student;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepo studentRepo;
    private final SchoolRepo schoolRepo;

    @Transactional
    public Student saveStudent(Student student) {
        SchoolEntity schoolEntity = schoolRepo.findById(student.getSchoolId()).orElseThrow(() -> new E2DMissingException("id-" + student.getSchoolId()));
        StudentEntity studentEntity = StudentMapper.mapToEntity(student, schoolEntity);
        studentRepo.save(studentEntity);
        return StudentMapper.mapToModel(studentEntity);
    }

    public Student getStudentById(Long id) {
        StudentEntity studentEntity = studentRepo.findById(id).orElseThrow(() -> new E2DMissingException("id-" + id));
        return StudentMapper.mapToModel(studentEntity);
    }

    public List<Student> getAllStudent() {
        List<StudentEntity> all = studentRepo.findAll();
        return all.stream().map(StudentMapper::mapToModel).collect(Collectors.toList());
    }

    public void deleteStudent(Long id) {
        studentRepo.delete(studentRepo.findById(id).orElseThrow(() -> new E2DMissingException("id-" + id)));
    }

    @Transactional
    public Student updateStudent(Long id, Student student) {
        StudentEntity studentEntity = studentRepo.findById(id).orElseThrow(() -> new E2DMissingException("id-" + student.getId()));
        SchoolEntity schoolEntity = schoolRepo.findById(student.getSchoolId()).orElseThrow(() -> new E2DMissingException("id-" + id));
        StudentMapper.mapToExistingEntity(studentEntity, student, schoolEntity);
        studentRepo.save(studentEntity);
        return StudentMapper.mapToModel(studentEntity);
    }

}
