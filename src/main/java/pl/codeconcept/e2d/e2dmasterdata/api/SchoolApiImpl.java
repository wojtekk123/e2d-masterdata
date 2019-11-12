package pl.codeconcept.e2d.e2dmasterdata.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.codeconcept.e2d.e2dmasterdata.service.SchoolService;
import pl.codeconcept.e2d.e2dmasterdata.api.SchoolApi;
import pl.codeconcept.e2d.e2dmasterdata.model.School;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class SchoolApiImpl implements SchoolApi {

    private final SchoolService schoolService;

    @Override
    public ResponseEntity<School> createSchool(@Valid School body) {
        return new ResponseEntity<>(schoolService.saveSchool(body), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteSchool(Long id) {
        schoolService.deleteSchool(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<School>> getAllSchool() {
        return new ResponseEntity<>(schoolService.getAllSchool(),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<School> getSchool(Long id) {
        return new ResponseEntity<>(schoolService.getSchoolId(id),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<School> updateSchool(Long id, @Valid School body) {
        return new ResponseEntity<>(schoolService.updateSchool(id,body),HttpStatus.OK);
    }
}
