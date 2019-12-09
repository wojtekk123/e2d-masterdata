package pl.codeconcept.e2d.e2dmasterdata.service.mappers;

import pl.codeconcept.e2d.e2dmasterdata.database.entity.InstructorEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.SchoolEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.enums.UserType;
import pl.codeconcept.e2d.e2dmasterdata.model.Instructor;

public class InstructorMapper {


    public static InstructorEntity mapToEntity(Instructor instructor, SchoolEntity schoolEntity, UserType userType, Long id) {
        InstructorEntity instructorEntity = new InstructorEntity();
        instructorEntity.setUserEntity(UserMapper.mapToEntity(instructor.getUser(), userType, id));
        instructorEntity.setSchool(schoolEntity);
        return instructorEntity;
    }

    public static void mapToExistingEntity(InstructorEntity instructorEntity, Instructor instructor, SchoolEntity schoolEntity) {
        mapToEntity(instructorEntity, schoolEntity);
        UserMapper.mapToExistingEntity(instructorEntity.getUserEntity(), instructor.getUser());
    }


    public static Instructor mapToModel(InstructorEntity instructorEntity) {
        Instructor instructor = new Instructor();
        instructor.setId(instructorEntity.getId());
        instructor.setUser(UserMapper.mapToModel(instructorEntity.getUserEntity()));
        instructor.setSchoolId(instructorEntity.getSchool().getId());
        return instructor;
    }

    private static void mapToEntity(InstructorEntity instructorEntity, SchoolEntity schoolEntity) {
        instructorEntity.setSchool(schoolEntity);
    }
}
