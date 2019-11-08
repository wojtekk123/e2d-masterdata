package pl.codeconscept.e2d.e2d_masterdata.service.mappers;

import pl.codeconscept.e2d.e2d_masterdata.database.entity.SchoolEntity;
import pl.codeconscept.e2d.e2d_masterdata.database.entity.StudentEntity;
import pl.codeconscept.e2d.e2d_masterdata.database.entity.UserEntity;
import pl.codeconscept.e2d.e2dmasterdata.model.Student;
import pl.codeconscept.e2d.e2dmasterdata.model.User;

public class StudentMapper {

    public static StudentEntity mapToEntity(User user, Student student, SchoolEntity schoolEntity) {
        // nie mapuje ID
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setUserEntity(UserMapper.mapToEntity(user));
        studentEntity.setSchool(schoolEntity);
        studentEntity.setStartEducationDate(student.getStartEducation());
        studentEntity.setEndEducationDate(student.getStartEducation());
        return studentEntity;
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

    public static StudentEntity mapToEntitySave(UserEntity userEntity, Student student, SchoolEntity schoolEntity) {
        // nie mapuje ID
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setUserEntity(userEntity);
        studentEntity.setSchool(schoolEntity);
        studentEntity.setStartEducationDate(student.getStartEducation());
        studentEntity.setEndEducationDate(student.getStartEducation());
        return studentEntity;
    }



}