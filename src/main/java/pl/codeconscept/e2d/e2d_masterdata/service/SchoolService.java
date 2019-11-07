package pl.codeconscept.e2d.e2d_masterdata.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.codeconscept.e2d.e2d_masterdata.database.entity.SchoolEntity;
import pl.codeconscept.e2d.e2d_masterdata.database.repository.SchoolRepo;
import pl.codeconscept.e2d.e2d_masterdata.dto.School;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolService {

    private final SchoolRepo schoolRepo;

    public SchoolEntity saveSchool(School schoolDto) {
        SchoolEntity school = new SchoolEntity(schoolDto.getName(), schoolDto.getOfficialName());
        return schoolRepo.save(school);
    }

    public SchoolEntity getSchoolId(Integer id) {
        return schoolRepo.findById(id.longValue()).orElseThrow(IllegalArgumentException::new);
    }

    public List<SchoolEntity> getAllschool() {
        return schoolRepo.findAll();
    }

    public void deleteSchool(Integer id) {
        schoolRepo.delete(schoolRepo.findById(id.longValue()).orElseThrow(IllegalArgumentException::new));
    }

    public SchoolEntity updateSchool(Integer id, School schoolDto) {
        return schoolRepo.findById(id.longValue()).map(school -> {
            school.setName(schoolDto.getName());
            school.setOfficialName(schoolDto.getOfficialName());
            return schoolRepo.save(school);
        }).get();
    }
}