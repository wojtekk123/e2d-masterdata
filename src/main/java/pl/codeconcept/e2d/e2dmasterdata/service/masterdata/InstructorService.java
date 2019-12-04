package pl.codeconcept.e2d.e2dmasterdata.service.masterdata;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.InstructorEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.SchoolEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.StudentEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.UserEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.enums.UserType;
import pl.codeconcept.e2d.e2dmasterdata.database.repository.InstructorRepo;
import pl.codeconcept.e2d.e2dmasterdata.database.repository.SchoolRepo;
import pl.codeconcept.e2d.e2dmasterdata.database.repository.StudentRepo;
import pl.codeconcept.e2d.e2dmasterdata.exception.E2DAccessDenied;
import pl.codeconcept.e2d.e2dmasterdata.exception.E2DMissingException;
import pl.codeconcept.e2d.e2dmasterdata.model.Student;
import pl.codeconcept.e2d.e2dmasterdata.service.mappers.InstructorMapper;
import pl.codeconcept.e2d.e2dmasterdata.model.Instructor;
import pl.codeconcept.e2d.e2dmasterdata.service.mappers.StudentMapper;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service

public class InstructorService extends AbstractMasterdataService {

    @Autowired
    private  InstructorRepo instructorRepo;
    @Autowired
    private  SchoolRepo schoolRepo;

    public InstructorService(SchoolRepo schoolRepo, InstructorRepo instructorRepo, StudentRepo studentRepo) {
        super(schoolRepo, instructorRepo, studentRepo);
    }


    @Transactional
    public ResponseEntity<Instructor> saveInstructor(Instructor instructor, UserEntity userEntity) {

        try {
            SchoolEntity schoolEntity = schoolRepo.findById(instructor.getSchoolId()).orElseThrow(() -> new E2DMissingException("id-" + instructor.getSchoolId()));

            if (!userEntity.getType().equals(UserType.ADMIN)) {
                SchoolEntity schoolEntityForUser = getSchoolEntityForUser(userEntity);

                if (!(userEntity.getType().equals(UserType.SCHOOL)) || !(schoolEntityForUser.getId() == instructor.getSchoolId())) {
                    throw new RuntimeException();
                }
            }
            InstructorEntity instructorEntity = InstructorMapper.mapToEntity(instructor, schoolEntity);
            instructorRepo.save(instructorEntity);
            return new ResponseEntity<>(InstructorMapper.mapToModel(instructorEntity), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            throw new E2DMissingException("id- " + instructor.getId());
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new E2DAccessDenied("get-id:" + instructor.getId());
        }
    }


    public ResponseEntity<List<Instructor>> getAllInstructor(UserEntity userEntity) {
        try {
            SchoolEntity schoolFromUser = getSchoolEntityForUser(userEntity);
            List<InstructorEntity> all = instructorRepo.findAll();

            if (userEntity.getType().equals(UserType.ADMIN)) {
                List<Instructor> allInstructor = all.stream().map(InstructorMapper::mapToModel).collect(Collectors.toList());
                return new ResponseEntity<>(allInstructor, HttpStatus.OK);
            } else if (userEntity.getType().equals(UserType.SCHOOL)) {
                Stream<InstructorEntity> studentEntityStream = all.stream().filter(e -> e.getSchool().getId() == schoolFromUser.getId());
                List<Instructor> collectInstructor= studentEntityStream.map(InstructorMapper::mapToModel).collect(Collectors.toList());
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
            SchoolEntity schoolFromUser = getSchoolEntityForUser(userEntity);
            InstructorEntity instructorEntity = instructorRepo.findById(id).orElseThrow(() -> new E2DMissingException("id-" + id));
            if (!userEntity.getType().equals(UserType.ADMIN)) {
                if (!(userEntity.getType().equals(UserType.SCHOOL)) || !(schoolFromUser.getId() == instructorEntity.getId())) {
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
            SchoolEntity schoolEntityForUser = getSchoolEntityForUser(userEntity);
            InstructorEntity instructorEntity = instructorRepo.findById(id).orElseThrow(() -> new E2DMissingException("id-" + id));
            if (!userEntity.getType().equals(UserType.ADMIN)) {
                if (!(userEntity.getType().equals(UserType.SCHOOL)) || !(schoolEntityForUser.getId() == instructorEntity.getSchool().getId())) {
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
    public Instructor updateInstructor(Long id, Instructor instructor) {
        InstructorEntity instructorEntity = instructorRepo.findById(id).orElseThrow(() -> new E2DMissingException("id-" + instructor.getId()));
        SchoolEntity schoolEntity = schoolRepo.findById(instructor.getSchoolId()).orElseThrow(() -> new E2DMissingException("id-" + id));
        InstructorMapper.mapToExistingEntity(instructorEntity, instructor, schoolEntity);
        return InstructorMapper.mapToModel(instructorEntity);
    }

    @Transactional
    public ResponseEntity<Instructor> updateInstructor(Long id, Instructor instructor, UserEntity userEntity) {

        try {
            SchoolEntity schoolEntityForUser = getSchoolEntityForUser(userEntity);
            InstructorEntity instructorEntity = instructorRepo.findById(id).orElseThrow(() -> new E2DMissingException("id-" + instructor.getId()));
            SchoolEntity schoolEntity = schoolRepo.findById(instructor.getSchoolId()).orElseThrow(() -> new E2DMissingException("id-" + id));

            if (!userEntity.getType().equals(UserType.ADMIN)) {
                if (instructorEntity== null || !(schoolEntityForUser.getId() == schoolEntity.getId()) || !(userEntity.getType().equals(UserType.SCHOOL))) {
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