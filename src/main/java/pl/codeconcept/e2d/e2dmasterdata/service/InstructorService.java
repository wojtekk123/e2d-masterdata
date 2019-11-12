package pl.codeconcept.e2d.e2dmasterdata.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.InstructorEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.SchoolEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.repository.InstructorRepo;
import pl.codeconcept.e2d.e2dmasterdata.database.repository.SchoolRepo;
import pl.codeconcept.e2d.e2dmasterdata.service.mappers.InstructorMapper;
import pl.codeconcept.e2d.e2dmasterdata.model.Instructor;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InstructorService {

    private final InstructorRepo instructorRepo;
    private final SchoolRepo schoolRepo;

    @Transactional
    public Instructor saveInstructor(Instructor instructor) {
        SchoolEntity schoolEntity = schoolRepo.findById(instructor.getSchoolId()).orElseThrow(() -> new IllegalArgumentException("School does not exist."));
        InstructorEntity saveInstructor = instructorRepo.save(InstructorMapper.mapToEntity(instructor, schoolEntity));
        return InstructorMapper.mapToModel(saveInstructor);
    }

    public List<Instructor> getAllInstructor() {
        List<InstructorEntity> all = instructorRepo.findAll();
        return all.stream().map(InstructorMapper::mapToModel).collect(Collectors.toList());
    }

    public Instructor getInstructorById(Long id) {
        InstructorEntity instructorEntity = instructorRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Instructor does not exist."));
        return InstructorMapper.mapToModel(instructorEntity);
    }

    public void deleteInstructor(Long id) {
        instructorRepo.delete(instructorRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Instructor does not exist.")));
    }

    @Transactional
    public Instructor updateInstructor(Long id, Instructor instructor) {
        InstructorEntity instructorEntity = instructorRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Student does not exist."));
        SchoolEntity schoolEntity = schoolRepo.findById(instructor.getSchoolId()).orElseThrow(() -> new IllegalArgumentException("School does not exist."));
        InstructorMapper.mapToExistingEntity(instructorEntity, instructor, schoolEntity);
        return InstructorMapper.mapToModel(instructorEntity);
    }
}