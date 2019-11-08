package pl.codeconscept.e2d.e2d_masterdata.service.mappers;

import lombok.RequiredArgsConstructor;
import pl.codeconscept.e2d.e2d_masterdata.database.entity.SchoolEntity;
import pl.codeconscept.e2d.e2d_masterdata.database.repository.SchoolRepo;
import pl.codeconscept.e2d.e2dmasterdata.model.School;

@RequiredArgsConstructor
public class SchoolMapper {

    private final SchoolRepo schoolRepo;
    private SchoolEntity schoolEntity;

    public static SchoolEntity mapToEntity(School school){

        SchoolEntity schoolEntity = new SchoolEntity();
        schoolEntity.setName(school.getName());
        schoolEntity.setOfficialName(school.getOfficialName());
        return schoolEntity;
    }

    public static  School mapToModel(SchoolEntity schoolEntity) {
        School school = new School();
        school.setId(schoolEntity.getId());
        school.setName(schoolEntity.getName());
        school.setOfficialName(schoolEntity.getOfficialName());
        return school;
    }
}
