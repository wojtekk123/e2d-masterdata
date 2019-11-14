package pl.codeconcept.e2d.e2dmasterdata.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.SchoolEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.repository.SchoolRepo;
import pl.codeconcept.e2d.e2dmasterdata.exception.E2DMissingException;
import pl.codeconcept.e2d.e2dmasterdata.service.mappers.SchoolMapper;
import pl.codeconcept.e2d.e2dmasterdata.model.School;

import java.util.List;
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
        SchoolEntity schoolEntity = schoolRepo.findById(id).orElseThrow(() -> new E2DMissingException("id-"+id));
        return SchoolMapper.mapToModel(schoolEntity);
    }

    public List<School> getAllSchool() {
        List<SchoolEntity> allSchool = schoolRepo.findAll();
        return allSchool.stream().map(SchoolMapper::mapToModel).collect(Collectors.toList());
    }

    public void deleteSchool(Long id) {
        schoolRepo.delete(schoolRepo.findById(id).orElseThrow(() -> new E2DMissingException("id-"+id)));
    }

    public School updateSchool(Long id, School school) {
        SchoolEntity schoolEntity = schoolRepo.findById(id).orElseThrow(() -> new E2DMissingException("id-"+id));
        SchoolMapper.mapToExistingEntity(schoolEntity, school);
        return SchoolMapper.mapToModel(schoolEntity);
    }
}