package pl.codeconscept.e2d.e2d_masterdata.service.mappers;

import pl.codeconscept.e2d.e2d_masterdata.database.entity.InstructorEntity;
import pl.codeconscept.e2d.e2d_masterdata.database.entity.SchoolEntity;
import pl.codeconscept.e2d.e2d_masterdata.database.entity.UserEntity;
import pl.codeconscept.e2d.e2dmasterdata.model.Instructor;
import pl.codeconscept.e2d.e2dmasterdata.model.User;


public class InstructorMapper {


    public static InstructorEntity mapToEntity(UserEntity userEntity, SchoolEntity schoolEntity) {
        InstructorEntity instructorEntity = new InstructorEntity();
        instructorEntity.setUserEntity(userEntity);
        instructorEntity.setSchool(schoolEntity);
        return instructorEntity;
    }


    public static Instructor mapToModel(InstructorEntity instructorEntity) {
        Instructor instructor = new Instructor();
        instructor.setId(instructorEntity.getId());
        instructor.setUser(UserMapper.mapToModel(instructorEntity.getUserEntity()));
        instructor.setSchoolId(instructorEntity.getSchool().getId());
        return instructor;
    }



}
