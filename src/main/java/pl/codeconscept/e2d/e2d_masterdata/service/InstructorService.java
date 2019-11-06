package pl.codeconscept.e2d.e2d_masterdata.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.codeconscept.e2d.e2d_masterdata.database.entity.InstructorEntity;
import pl.codeconscept.e2d.e2d_masterdata.database.entity.SchoolEntity;
import pl.codeconscept.e2d.e2d_masterdata.database.entity.UserEntity;
import pl.codeconscept.e2d.e2d_masterdata.database.repository.InstructorRepo;
import pl.codeconscept.e2d.e2d_masterdata.database.repository.SchoolRepo;
import pl.codeconscept.e2d.e2d_masterdata.database.repository.UserRepo;
import pl.codeconscept.e2d.e2d_masterdata.dto.Instructor;
import pl.codeconscept.e2d.e2d_masterdata.dto.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InstructorService {

    private final InstructorRepo instructorRepo;
    private final SchoolRepo schoolRepo;
    private final UserRepo userRepo;

    public UserEntity saveUser(Instructor instructorDto) {
        InstructorEntity instructor = saveInstructor(instructorDto);
        User userDto = instructorDto.getUserDto();
        UserEntity user = new UserEntity(userDto.getFirstName(), userDto.getSecondName(), userDto.getEmail(), userDto.getPhoneNumber(), null, instructor);
        return userRepo.save(user);
    }

    public List<UserEntity> getAllInstructor() {
        return userRepo.findAll().stream().filter(e -> (e.getStudent() == null)).collect(Collectors.toList());
    }

    public UserEntity getInstructorById(Integer id) {
        return userRepo.findByInstructorId(id.longValue());
    }

    public void deleteInstructor(Integer id) {
        userRepo.delete(userRepo.findByInstructorId(id.longValue()));

    }

    public UserEntity updateInstructor(Integer id, Instructor instructorDto) {
        User userDto = instructorDto.getUserDto();
        InstructorEntity mapInstructor = mapObject(instructorDto);
        Optional<UserEntity> userOptional = Optional.of(userRepo.findByInstructorId(id.longValue()));
        Long idStd = userOptional.get().getInstructor().getId();
        return userOptional.map(user -> {
            user.setFirstName(userDto.getFirstName());
            user.setSecondName(userDto.getSecondName());
            user.setEmail(userDto.getEmail());
            user.setPhoneNumber(userDto.getEmail());
            user.setStudent(null);
            user.setInstructor(instructorRepo.findById(idStd).map(inst -> {
                inst.setSchoolId(mapInstructor.getSchoolId());
                return instructorRepo.save(inst);
            }).get());
            return userRepo.save(user);
        }).get();
    }

    private InstructorEntity saveInstructor(Instructor instructorDto) {
        SchoolEntity school = schoolRepo.findById(instructorDto.getSchoolId()).get();
        InstructorEntity instructor = new InstructorEntity(school);
        return instructorRepo.save(instructor);
    }

    private InstructorEntity mapObject(Instructor instructorDto) {
        SchoolEntity school = schoolRepo.findById(instructorDto.getSchoolId()).get();
        return new InstructorEntity(school);
    }

}
