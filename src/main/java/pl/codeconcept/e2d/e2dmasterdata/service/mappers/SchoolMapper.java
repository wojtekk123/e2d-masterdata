package pl.codeconcept.e2d.e2dmasterdata.service.mappers;

import lombok.RequiredArgsConstructor;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.SchoolEntity;
import pl.codeconcept.e2d.e2dmasterdata.model.School;

@RequiredArgsConstructor
public class SchoolMapper {

    public static SchoolEntity mapToEntity(School school) {

        SchoolEntity schoolEntity = new SchoolEntity();
        schoolEntity.setName(school.getName());
        schoolEntity.setOfficialName(school.getOfficialName());
        return schoolEntity;
    }

    public static void mapToExistingEntity(SchoolEntity schoolEntity, School school) {
        schoolEntity.setName(school.getName());
        schoolEntity.setOfficialName(school.getOfficialName());
    }

    public static School mapToModel(SchoolEntity schoolEntity) {
        School school = new School();
        school.setId(schoolEntity.getId());
        school.setName(schoolEntity.getName());
        school.setOfficialName(schoolEntity.getOfficialName());
        return school;
    }
}
