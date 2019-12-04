package pl.codeconcept.e2d.e2dmasterdata.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.UserEntity;
import pl.codeconcept.e2d.e2dmasterdata.service.masterdata.SchoolService;
import pl.codeconcept.e2d.e2dmasterdata.model.School;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class SchoolApiImpl extends AbstractApi  implements SchoolApi {

    private final SchoolService schoolService;

    @Override
    public ResponseEntity<School> createSchool(@Valid School body) {
        UserEntity userEntity = getUserFromToken();
        return schoolService.saveSchool(body,userEntity);
    }


    @Override
    public ResponseEntity<List<School>> getAllSchool() {
        UserEntity userEntity= getUserFromToken();
        return schoolService.getAllSchool(userEntity);
    }

    @Override
    public ResponseEntity<Void> deleteSchool(Long id) {
        UserEntity userEntity= getUserFromToken();
        return schoolService.deleteSchool(id,userEntity);
    }
    @Override
    public ResponseEntity<School> getSchool(Long id) {
        UserEntity userEntity = getUserFromToken();
      return   schoolService.getSchoolById(id,userEntity);

    }

    @Override
    public ResponseEntity<School> updateSchool(Long id, @Valid School body) {
        UserEntity userEntity = getUserFromToken();
        return schoolService.updateSchool(id,body,userEntity);
    }
}
