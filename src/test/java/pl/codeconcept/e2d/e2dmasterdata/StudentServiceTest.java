package pl.codeconcept.e2d.e2dmasterdata;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.SchoolEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.StudentEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.UserEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.enums.UserType;
import pl.codeconcept.e2d.e2dmasterdata.database.repository.SchoolRepo;
import pl.codeconcept.e2d.e2dmasterdata.database.repository.StudentRepo;
import pl.codeconcept.e2d.e2dmasterdata.exception.E2DAccessDenied;
import pl.codeconcept.e2d.e2dmasterdata.service.masterdata.StudentService;

import java.util.Date;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceTest {

    @Mock
    SchoolRepo schoolRepo;

    @Mock
    StudentRepo studentRepo;

    @InjectMocks
    StudentService studentService;

    private final UserEntity userEntity = new UserEntity("Marek", 1L, "Nowak", UserType.STUDENT, "pkvs@o2.pl", "+12 123123322");
    private final SchoolEntity schoolEntity = new SchoolEntity(userEntity, "Szkoła Podstawowa", "Szkoła Podstawowa nr 9 im. Króla Jana III Sobieskiego");
    private final Long id = 1L;
    private final Date startDate  = new Date(2013-10-24);
    private final Date endDate  = new Date(2013-10-24);

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        BDDMockito.given(schoolRepo.findById(id)).willReturn(prepareMockData());
        BDDMockito.given(studentRepo.findById(id)).willReturn(prepareMockDatagetStudent());
    }

    @Test
    public void schoolShouldByNotNull() {
        Optional<SchoolEntity> byId = schoolRepo.findById(id);
        Assert.assertNotNull(byId.get());
    }

    @Test
    public void studentShouldByNotNull() {
        Optional<StudentEntity> byId = studentRepo.findById(id);
        Assert.assertNotNull(byId.get());
    }

    @Test(expected = E2DAccessDenied.class)
    public void whenNoAccessThrowException() {
        studentService.getStudentById(id, userEntity);
    }


    private Optional<SchoolEntity> prepareMockData() {
        return Optional.of(schoolEntity);
    }

    private Optional<StudentEntity> prepareMockDatagetStudent() {
        StudentEntity studentEntity=  new StudentEntity(userEntity,schoolEntity,startDate,endDate);
        return Optional.of(studentEntity);
    }
}
