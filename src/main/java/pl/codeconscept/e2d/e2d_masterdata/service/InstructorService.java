package pl.codeconscept.e2d.e2d_masterdata.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.codeconscept.e2d.e2d_masterdata.database.entity.InstructorEntity;
import pl.codeconscept.e2d.e2d_masterdata.database.entity.SchoolEntity;
import pl.codeconscept.e2d.e2d_masterdata.database.entity.StudentEntity;
import pl.codeconscept.e2d.e2d_masterdata.database.entity.UserEntity;
import pl.codeconscept.e2d.e2d_masterdata.database.repository.InstructorRepo;
import pl.codeconscept.e2d.e2d_masterdata.database.repository.SchoolRepo;
import pl.codeconscept.e2d.e2d_masterdata.database.repository.UserRepo;
import pl.codeconscept.e2d.e2d_masterdata.service.mappers.InstructorMapper;
import pl.codeconscept.e2d.e2d_masterdata.service.mappers.StudentMapper;
import pl.codeconscept.e2d.e2d_masterdata.service.mappers.UserMapper;
import pl.codeconscept.e2d.e2dmasterdata.model.Instructor;
import pl.codeconscept.e2d.e2dmasterdata.model.Student;
import pl.codeconscept.e2d.e2dmasterdata.model.User;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InstructorService {

    private final InstructorRepo instructorRepo;
    private final SchoolRepo schoolRepo;
    private final UserRepo userRepo;

    @Transactional
    public Instructor saveInstructor(Instructor instructor) {
        SchoolEntity schoolEntity = schoolRepo.findById(instructor.getSchoolId()).orElseThrow(IllegalArgumentException::new);
        UserEntity savedUser = userRepo.save(UserMapper.mapToEntity(instructor.getUser()));
        InstructorEntity saveInstructor = instructorRepo.save(InstructorMapper.mapToEntity(savedUser, schoolEntity));

        if (saveInstructor == null) {
            throw new NullPointerException();
        }
        return InstructorMapper.mapToModel(saveInstructor);

    }
    public List<Instructor> getAllInstructor() {
        List<InstructorEntity> all = instructorRepo.findAll();
        return all.stream().map(InstructorMapper::mapToModel).collect(Collectors.toList());
    }

    public Instructor getInstructorById(Long id) {
        InstructorEntity instructorEntity = instructorRepo.findById(id).orElseThrow(IllegalArgumentException::new);
        return InstructorMapper.mapToModel(instructorEntity);
    }

    public void deleteInstructor(Long id) {
        instructorRepo.delete(instructorRepo.findById(id).orElseThrow(IllegalArgumentException::new));
    }

    public Instructor updateInstructor(Long id, Instructor instructor) {
        User userToChange = instructor.getUser();
        Long idUser = instructor.getUser().getId();

        InstructorEntity instructorEntity = instructorRepo.findById(id).map(inst -> {
            inst.setUserEntity(userRepo.findById(idUser).map(user -> {
                user.setFirstName(userToChange.getFirstName());
                user.setSecondName(userToChange.getSecondName());
                user.setEmail(userToChange.getEmail());
                user.setPhoneNumber(userToChange.getPhoneNumber());
                return user;
            }).orElseThrow(IllegalArgumentException::new));
            inst.setSchool(schoolRepo.findById(instructor.getSchoolId()).orElseThrow(IllegalArgumentException::new));
            return inst;
        }).orElseThrow(IllegalArgumentException::new);

        return InstructorMapper.mapToModel(instructorRepo.save(instructorEntity));
    }
}