package pl.codeconcept.e2d.e2dmasterdata.service.mappers;

import pl.codeconcept.e2d.e2dmasterdata.database.entity.SchoolEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.StudentEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.enums.UserType;
import pl.codeconcept.e2d.e2dmasterdata.model.Student;

public class StudentMapper {

    public static StudentEntity mapToEntity(Student student, SchoolEntity schoolEntity, UserType userType, Long id) {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setUserEntity(UserMapper.mapToEntity(student.getUser(), userType,id));
        mapToEntity(studentEntity, student, schoolEntity);
        return studentEntity;
    }

    public static void mapToExistingEntity(StudentEntity studentEntity, Student student, SchoolEntity schoolEntity) {
        mapToEntity(studentEntity, student, schoolEntity);
        UserMapper.mapToExistingEntity(studentEntity.getUserEntity(), student.getUser());
    }

    public static Student mapToModel(StudentEntity studentEntity) {
        Student student = new Student();
        student.setId(studentEntity.getId());
        student.setUser(UserMapper.mapToModel(studentEntity.getUserEntity()));
        student.setSchoolId(studentEntity.getSchool().getId());
        student.setStartEducation(studentEntity.getStartEducationDate());
        student.setEndEducation(studentEntity.getEndEducationDate());
        return student;
    }

    private static void mapToEntity(StudentEntity studentEntity, Student student, SchoolEntity schoolEntity) {
        studentEntity.setSchool(schoolEntity);
        studentEntity.setStartEducationDate(student.getStartEducation());
        studentEntity.setEndEducationDate(student.getStartEducation());
    }
}