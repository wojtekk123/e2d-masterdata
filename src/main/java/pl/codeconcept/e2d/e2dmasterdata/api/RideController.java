package pl.codeconcept.e2d.e2dmasterdata.api;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.codeconcept.e2d.e2dmasterdata.model.UserId;
import pl.codeconcept.e2d.e2dmasterdata.service.masterdata.UserDataService;


@RestController
@RequiredArgsConstructor
public class RideController implements RideApi {

    private final UserDataService userDataService;

    @Override
    public ResponseEntity<UserId> getInstructorByAuthId(Long id) {
        return userDataService.getInstructorByAuthId(id);
    }

    @Override
    public ResponseEntity<UserId> getInstructorById(Long id) {
        return userDataService.getInstructorById(id);
    }

    @Override
    public ResponseEntity<UserId> getStudentById(Long id) {
        return userDataService.getStudentById(id);
    }

    @Override
    public ResponseEntity<UserId> getStudentByAuthID(Long id) {
        return userDataService.getStudentByAuthID(id);
    }

    @Override
    public ResponseEntity<UserId> getSchoolByAuthId(Long id) {
        return userDataService.getSchoolByAuthId(id);
    }
}
