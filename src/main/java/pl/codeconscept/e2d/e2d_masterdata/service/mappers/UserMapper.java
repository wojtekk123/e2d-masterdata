package pl.codeconscept.e2d.e2d_masterdata.service.mappers;

import pl.codeconscept.e2d.e2d_masterdata.database.entity.SchoolEntity;
import pl.codeconscept.e2d.e2d_masterdata.database.entity.StudentEntity;
import pl.codeconscept.e2d.e2d_masterdata.database.entity.UserEntity;
import pl.codeconscept.e2d.e2dmasterdata.model.Instructor;
import pl.codeconscept.e2d.e2dmasterdata.model.School;
import pl.codeconscept.e2d.e2dmasterdata.model.Student;
import pl.codeconscept.e2d.e2dmasterdata.model.User;

public class UserMapper {

    public static UserEntity mapToEntity (User user){
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(user.getFirstName());
        userEntity.setSecondName(user.getSecondName());
        userEntity.setEmail(user.getEmail());
        userEntity.setPhoneNumber(user.getPhoneNumber());
        return userEntity;

    }

    public static User mapToModel (UserEntity userEntity){
        User user = new User();
        user.setId(userEntity.getId());
        user.setFirstName(userEntity.getFirstName());
        user.setSecondName(userEntity.getSecondName());
        user.setEmail(userEntity.getEmail());
        user.setPhoneNumber(userEntity.getPhoneNumber());
        return user;
    }




//    public static UserEntity toEntityFromInstructor(Instructor instructor, School school) {
//        UserEntity userEntity = toEntity(instructor.getUser());
//        userEntity.setInstructor(InstructorMapper.mapToEntity(instructor,school));
//        return userEntity;
//    }
//
//    public static User mapToModel(UserEntity userEntity) {
//        User user = new User();
//        user.setFirstName(userEntity.getFirstName());
//        user.setSecondName(userEntity.getSecondName());
//        user.setEmail(userEntity.getEmail());
//        user.setPhoneNumber(userEntity.getPhoneNumber());
//        return user;
//    }
//
//    private static UserEntity toEntity(User user) {
//        UserEntity userEntity = new UserEntity();
//        userEntity.setFirstName(user.getFirstName());
//        userEntity.setSecondName(user.getSecondName());
//        userEntity.setEmail(user.getEmail());
//        userEntity.setPhoneNumber(user.getPhoneNumber());
//        return userEntity;
//    }
}

//public static UserEntity toEntityFromStudent(Student student, School school) {
//        UserEntity userEntity = toEntity(student.getUser());
//
//    }
//
//    public static UserEntity toEntityFromStudent(Student student, StudentEntity studentEntity, School school) {
//        userEntity.setStudent(StudentMapper.mapToEntity(student,school));
//        return userEntity;
//    }