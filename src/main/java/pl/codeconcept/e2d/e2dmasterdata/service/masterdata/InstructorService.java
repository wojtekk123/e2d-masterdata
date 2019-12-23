package pl.codeconcept.e2d.e2dmasterdata.service.masterdata;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.InstructorEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.SchoolEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.UserEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.enums.UserType;
import pl.codeconcept.e2d.e2dmasterdata.database.repository.InstructorRepo;
import pl.codeconcept.e2d.e2dmasterdata.database.repository.SchoolRepo;
import pl.codeconcept.e2d.e2dmasterdata.database.repository.StudentRepo;
import pl.codeconcept.e2d.e2dmasterdata.exception.E2DAccessDenied;
import pl.codeconcept.e2d.e2dmasterdata.exception.E2DExistException;
import pl.codeconcept.e2d.e2dmasterdata.exception.E2DMissingException;
import pl.codeconcept.e2d.e2dmasterdata.model.AuthBack;
import pl.codeconcept.e2d.e2dmasterdata.model.InstructorAndAuth;
import pl.codeconcept.e2d.e2dmasterdata.service.jwt.JwtAuthFilter;
import pl.codeconcept.e2d.e2dmasterdata.service.mappers.InstructorMapper;
import pl.codeconcept.e2d.e2dmasterdata.model.Instructor;
import pl.codeconcept.e2d.e2dmasterdata.service.template.TemplateRestQueries;


import javax.validation.ValidationException;
import java.rmi.ConnectIOException;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service

public class InstructorService extends AbstractMasterdataService {

    @Autowired
    private InstructorRepo instructorRepo;
    @Autowired
    private SchoolRepo schoolRepo;
    @Autowired
    private TemplateRestQueries templateRestQueries;
    @Autowired
    JwtAuthFilter jwtAuthFilter;


    public InstructorService(SchoolRepo schoolRepo, InstructorRepo instructorRepo, StudentRepo studentRepo) {
        super(schoolRepo, instructorRepo, studentRepo);
    }

    public ResponseEntity<Instructor> saveInstructor(InstructorAndAuth instructorAndAuth, UserEntity userEntity) {

        try {
            SchoolEntity schoolEntity = schoolRepo.findById(instructorAndAuth.getInstructor().getSchoolId()).orElseThrow(() -> new E2DMissingException("id-" + instructorAndAuth.getInstructor().getSchoolId()));

            if (!userEntity.getType().equals(UserType.ADMIN)) {
                SchoolEntity schoolEntityForUser = getSchoolEntityForUser(userEntity);

                if (!(userEntity.getType().equals(UserType.SCHOOL)) || !(schoolEntityForUser.getId().equals(instructorAndAuth.getInstructor().getSchoolId()))) {
                    throw new RuntimeException();
                }
            }

            AuthBack userData = templateRestQueries.getUserData(jwtAuthFilter.getToken(), instructorAndAuth.getAuth(), UserType.INSTRUCTOR);
            InstructorEntity instructorEntity = InstructorMapper.mapToEntity(instructorAndAuth.getInstructor(), schoolEntity, UserType.INSTRUCTOR, userData.getIdAuth());
            instructorRepo.save(instructorEntity);
            return new ResponseEntity<>(InstructorMapper.mapToModel(instructorEntity), HttpStatus.OK);

        } catch (ConnectIOException e) {
            throw new E2DExistException("Connection problem");
        } catch (DataAccessException | ValidationException e) {
            throw new E2DExistException("Wrong number or email");
        } catch (InvalidParameterException e) {
            throw new E2DMissingException("user already exist :" + instructorAndAuth.getAuth().getUsername());
        } catch (IllegalArgumentException e) {
            throw new E2DMissingException("id- " + instructorAndAuth.getInstructor().getId());
        } catch (RuntimeException e) {
            throw new E2DAccessDenied("school id: " + instructorAndAuth.getInstructor().getSchoolId());
        }
    }


    public ResponseEntity<List<Instructor>> getAllInstructor(UserEntity userEntity) {
        try {
            SchoolEntity schoolFromUser = getSchoolEntityForUser(userEntity);
            List<InstructorEntity> all = instructorRepo.findAll();

            if (userEntity.getType().equals(UserType.ADMIN)) {
                List<Instructor> allInstructor = all.stream().map(InstructorMapper::mapToModel).collect(Collectors.toList());
                return new ResponseEntity<>(allInstructor, HttpStatus.OK);

            } else if (userEntity.getType().equals(UserType.SCHOOL) || userEntity.getType().equals(UserType.INSTRUCTOR)) {
                Stream<InstructorEntity> instructorEntityStream = all.stream().filter(e -> e.getSchool().getId().equals(schoolFromUser.getId()));
                List<Instructor> collectInstructor = instructorEntityStream.map(InstructorMapper::mapToModel).collect(Collectors.toList());
                return new ResponseEntity<>(collectInstructor, HttpStatus.OK);
            }
            return ResponseEntity.badRequest().build();

        } catch (IllegalArgumentException e) {
            throw new E2DMissingException("id: " + userEntity.getId());
        } catch (RuntimeException e) {
            throw new E2DAccessDenied("get all:");
        }

    }

    public ResponseEntity<Instructor> getInstructorById(Long id, UserEntity userEntity) {

        try {
            InstructorEntity instructorEntity = instructorRepo.findById(id).orElseThrow(() -> new E2DMissingException("id-" + id));

            if (!userEntity.getType().equals(UserType.ADMIN)) {
                SchoolEntity schoolFromUser = getSchoolEntityForUser(userEntity);

                if (!((userEntity.getType().equals(UserType.SCHOOL)) || (userEntity.getType().equals(UserType.INSTRUCTOR))) || !(schoolFromUser.getId().equals(instructorEntity.getId()))) {
                    throw new RuntimeException();
                }
            }
            return new ResponseEntity<>(InstructorMapper.mapToModel(instructorEntity), HttpStatus.OK);

        } catch (IllegalArgumentException e) {
            throw new E2DMissingException("id- " + id);
        } catch (RuntimeException e) {
            throw new E2DAccessDenied("get-id:" + id);
        }
    }


    @Transactional
    public ResponseEntity<Void> deleteInstructor(Long id, UserEntity userEntity) {

        try {
            InstructorEntity instructorEntity = instructorRepo.findById(id).orElseThrow(() -> new E2DMissingException("id-" + id));

            if (!userEntity.getType().equals(UserType.ADMIN)) {
                SchoolEntity schoolEntityForUser = getSchoolEntityForUser(userEntity);

                if (!(userEntity.getType().equals(UserType.SCHOOL)) || !(schoolEntityForUser.getId().equals(instructorEntity.getSchool().getId()))) {
                    throw new RuntimeException();
                }
            }
            instructorRepo.delete(instructorEntity);
            return ResponseEntity.ok().build();

        } catch (IllegalArgumentException e) {
            throw new E2DMissingException("id- " + id);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new E2DAccessDenied("get-id:" + id);
        }
    }

    @Transactional
    public ResponseEntity<Instructor> updateInstructor(Long id, Instructor instructor, UserEntity userEntity) {

        try {
            InstructorEntity instructorEntity = instructorRepo.findById(id).orElseThrow(() -> new E2DMissingException("id-" + instructor.getId()));
            SchoolEntity schoolEntity = schoolRepo.findById(instructor.getSchoolId()).orElseThrow(() -> new E2DMissingException("id-" + id));

            if (!userEntity.getType().equals(UserType.ADMIN)) {
                SchoolEntity schoolEntityForUser = getSchoolEntityForUser(userEntity);

                if (instructorEntity == null || !(schoolEntityForUser.getId().equals(schoolEntity.getId())) || !(userEntity.getType().equals(UserType.SCHOOL))) {
                    throw new RuntimeException();
                }
            }
            InstructorMapper.mapToExistingEntity(instructorEntity, instructor, schoolEntity);
            instructorRepo.save(instructorEntity);
            return new ResponseEntity<>(InstructorMapper.mapToModel(instructorEntity), HttpStatus.OK);

        } catch (IllegalArgumentException e) {
            throw new E2DMissingException("id- " + id);
        } catch (RuntimeException e) {
            throw new E2DAccessDenied("update-id:" + id);
        }

    }
}