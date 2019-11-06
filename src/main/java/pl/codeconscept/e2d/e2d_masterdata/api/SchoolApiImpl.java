package pl.codeconscept.e2d.e2d_masterdata.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.codeconscept.e2d.e2d_masterdata.database.entity.SchoolEntity;
import pl.codeconscept.e2d.e2d_masterdata.database.repository.SchoolRepo;
import pl.codeconscept.e2d.e2d_masterdata.service.SchoolService;
import pl.codeconscept.e2d.e2dmasterdata.api.SchoolApi;
import pl.codeconscept.e2d.e2d_masterdata.dto.School;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class SchoolApiImpl implements SchoolApi {

    private final SchoolRepo schoolRepo;
    private final SchoolService schoolService;

    @Override
    public ResponseEntity<SchoolEntity> createSchool(@Valid School body) {
        SchoolEntity schoolEntity = schoolService.saveSchool(body);
        return new ResponseEntity<>(schoolEntity, HttpStatus.OK);
    }
    @Override
    public ResponseEntity<Void> deleteSchool(Integer id) {
        schoolService.deleteSchool(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<SchoolEntity>> getAllSchool() {
        List<SchoolEntity> allschool = schoolService.getAllschool();
        return new ResponseEntity<>(allschool,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SchoolEntity> getSchool(Integer id) {
        SchoolEntity schoolId = schoolService.getSchoolId(id);
        return new ResponseEntity<>(schoolId,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SchoolEntity> updateSchool(Integer id, @Valid School body) {
        SchoolEntity schoolEntity = schoolService.updateSchool(id, body);
        return new ResponseEntity<>(schoolEntity,HttpStatus.OK);
    }
}
