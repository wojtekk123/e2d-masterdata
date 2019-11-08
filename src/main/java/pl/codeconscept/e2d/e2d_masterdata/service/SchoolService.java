package pl.codeconscept.e2d.e2d_masterdata.service;

import lombok.Lombok;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.codeconscept.e2d.e2d_masterdata.database.entity.SchoolEntity;
import pl.codeconscept.e2d.e2d_masterdata.database.repository.SchoolRepo;
import pl.codeconscept.e2d.e2d_masterdata.service.mappers.SchoolMapper;
import pl.codeconscept.e2d.e2dmasterdata.model.School;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SchoolService {

    private final SchoolRepo schoolRepo;

    public School saveSchool(School school) {
        SchoolEntity savedSchool = schoolRepo.save(SchoolMapper.mapToEntity(school));
        return SchoolMapper.mapToModel(savedSchool);
    }


    public School getSchoolId(Long id) {
        SchoolEntity schoolEntity = schoolRepo.findById(id).orElseThrow(IllegalArgumentException::new);
        return SchoolMapper.mapToModel(schoolEntity);
    }

    public List<School> getAllSchool() {
        List<SchoolEntity> allSchool = schoolRepo.findAll();
        return allSchool.stream().map(SchoolMapper::mapToModel).collect(Collectors.toList());
    }

    public void deleteSchool(Long id) {
        schoolRepo.delete(schoolRepo.findById(id).orElseThrow(IllegalArgumentException::new));
    }

    public School updateSchool(Long id, School school) {
        SchoolEntity schoolToChange = schoolRepo.findById(id).map(s -> {
            s.setName(school.getName());
            s.setOfficialName(school.getOfficialName());
            return schoolRepo.save(s);
        }).orElseThrow(IllegalArgumentException::new);
        return SchoolMapper.mapToModel(schoolToChange);
    }
}