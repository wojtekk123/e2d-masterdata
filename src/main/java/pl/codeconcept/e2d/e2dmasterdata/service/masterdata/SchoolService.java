package pl.codeconcept.e2d.e2dmasterdata.service.masterdata;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.CarEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.SchoolEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.UserEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.enums.UserType;
import pl.codeconcept.e2d.e2dmasterdata.database.repository.InstructorRepo;
import pl.codeconcept.e2d.e2dmasterdata.database.repository.SchoolRepo;
import pl.codeconcept.e2d.e2dmasterdata.database.repository.StudentRepo;
import pl.codeconcept.e2d.e2dmasterdata.exception.E2DAccessDenied;
import pl.codeconcept.e2d.e2dmasterdata.exception.E2DMissingException;
import pl.codeconcept.e2d.e2dmasterdata.model.Car;
import pl.codeconcept.e2d.e2dmasterdata.service.jwt.JwtAuthFilter;
import pl.codeconcept.e2d.e2dmasterdata.service.mappers.CarMapper;
import pl.codeconcept.e2d.e2dmasterdata.service.mappers.SchoolMapper;
import pl.codeconcept.e2d.e2dmasterdata.model.School;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class SchoolService extends AbstractMasterdataService {

    @Autowired
    private SchoolRepo schoolRepo;

    public SchoolService(SchoolRepo schoolRepo, InstructorRepo instructorRepo, StudentRepo studentRepo) {
        super(schoolRepo, instructorRepo, studentRepo);
    }

    public ResponseEntity<School> saveSchool(School school, UserEntity userEntity) {

        try {
            if (!userEntity.getType().equals(UserType.ADMIN)) {
                throw new RuntimeException();
            }
            SchoolEntity savedSchool = schoolRepo.save(SchoolMapper.mapToEntity(school));
            return new ResponseEntity<>(SchoolMapper.mapToModel(savedSchool), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            throw new E2DMissingException("id- " + school.getId());
        } catch (RuntimeException e) {
            throw new E2DAccessDenied("create-school");
        }
    }

    public ResponseEntity<School> getSchoolById(Long id, UserEntity userEntity) {

        try {
            SchoolEntity schoolEntity = schoolRepo.findById(id).orElseThrow(() -> new E2DMissingException("id-" + id));
            SchoolEntity schoolEntityForUser = getSchoolEntityForUser(userEntity);
            if (!userEntity.getType().equals(UserType.ADMIN)) {
                if (schoolEntity == null || !(schoolEntityForUser.getId() == schoolEntity.getId())) {
                    throw new RuntimeException();
                }
            }
            return new ResponseEntity<>(SchoolMapper.mapToModel(schoolEntity), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            throw new E2DMissingException("id- " + id);
        } catch (RuntimeException e) {
            throw new E2DAccessDenied("get-id:" + id);
        }
    }

    public ResponseEntity<List<School>> getAllSchool(UserEntity userEntity) {

        List<SchoolEntity> all = schoolRepo.findAll();
        SchoolEntity schoolEntity = getSchoolEntityForUser(userEntity);

        try {
            if (userEntity.getType().equals(UserType.ADMIN)) {
                List<School> allSchool = all.stream().map(SchoolMapper::mapToModel).collect(Collectors.toList());
                return new ResponseEntity<>(allSchool, HttpStatus.OK);
            } else {
                List<SchoolEntity> filterSchool = all.stream().filter(e -> e.getId().equals(schoolEntity.getId())).collect(Collectors.toList());
                List<School> schools = filterSchool.stream().map(SchoolMapper::mapToModel).collect(Collectors.toList());
                return new ResponseEntity<>(schools, HttpStatus.OK);
            }
        } catch (IllegalArgumentException e) {
            throw new E2DMissingException("id- " + userEntity.getId());
        } catch (RuntimeException e) {
            throw new E2DAccessDenied("read-list");
        }
    }


    public ResponseEntity<Void> deleteSchool(Long id, UserEntity userEntity) {

        try {
            SchoolEntity schoolEntity = schoolRepo.findById(id).orElseThrow(() -> new E2DMissingException("id-" + id));
            if (!userEntity.getType().equals(UserType.ADMIN)) {
                throw new RuntimeException();
            }
            schoolRepo.delete(schoolEntity);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            throw new E2DMissingException("id- " + id);
        } catch (RuntimeException e) {
            throw new E2DAccessDenied("delete-id: " + id);
        }
    }


    public ResponseEntity<School> updateSchool(Long id, School school, UserEntity userEntity) {

        try {
            SchoolEntity schoolEntity = getSchoolEntityForUser(userEntity);
            SchoolEntity schoolToChange = schoolRepo.findById(id).orElseThrow(() -> new E2DMissingException("id-" + id));

            if (!userEntity.getType().equals(UserType.ADMIN)) {

                if (schoolEntity == null || !(schoolEntity.getId() == schoolToChange.getId()) || userEntity.getType().equals(UserType.STUDENT)) {
                    throw new RuntimeException();
                }
            }
            SchoolMapper.mapToExistingEntity(schoolToChange, school);
            return new ResponseEntity<>(SchoolMapper.mapToModel(schoolToChange), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            throw new E2DMissingException("id- " + id);
        } catch (RuntimeException e) {
            throw new E2DAccessDenied("update-id:" + id);
        }
    }
}