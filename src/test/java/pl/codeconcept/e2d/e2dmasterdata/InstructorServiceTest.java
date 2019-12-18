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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.InstructorEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.SchoolEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.UserEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.enums.UserType;
import pl.codeconcept.e2d.e2dmasterdata.database.repository.InstructorRepo;
import pl.codeconcept.e2d.e2dmasterdata.database.repository.SchoolRepo;
import pl.codeconcept.e2d.e2dmasterdata.model.*;
import pl.codeconcept.e2d.e2dmasterdata.service.masterdata.AbstractMasterdataService;
import pl.codeconcept.e2d.e2dmasterdata.service.masterdata.InstructorService;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class InstructorServiceTest {


    @Mock
    SchoolRepo schoolRepo;

    @Mock
    InstructorRepo instructorRepo;

    @Mock
    AbstractMasterdataService abstractMasterdataService;

    @InjectMocks
    InstructorService instructorService;

    private UserEntity userEntity = new UserEntity("Marek", 1L, "Nowak", UserType.ADMIN, "pkvs@o2.pl", "+12 123123322");
    private SchoolEntity schoolEntity = new SchoolEntity(userEntity, "Szkoła Podstawowa", "Szkoła Podstawowa nr 9 im. Króla Jana III Sobieskiego");
    private InstructorEntity instructorEntity = new InstructorEntity(schoolEntity, userEntity);
    private InstructorAndAuth instructorAndAuth = getInstructorAndAuth();
    private Long id = 1L;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        BDDMockito.given(schoolRepo.findById(id)).willReturn(prepareMockData());
        BDDMockito.given(instructorRepo.findById(id)).willReturn(prepareMockDataStudent());
//        BDDMockito.given(abstractMasterdataService.setUserInAuth(getInstructorAndAuth().getAuth(), UserType.INSTRUCTOR)).willReturn(prepareMockDataAuth());
    }

    @Test
    public void schoolShouldByNotNull() {
        Optional<SchoolEntity> byId = schoolRepo.findById(id);
        Assert.assertNotNull(byId.get());
    }

    @Test
    public void studentShouldByNotNull() {
        Optional<InstructorEntity> byId = instructorRepo.findById(id);
        Assert.assertNotNull(byId.get());
    }

//    @Test
//    public void shouldReturnInstructor() {
//        ResponseEntity<Instructor> instructorResponseEntity = instructorService.saveInstructor(instructorAndAuth, userEntity);
//        Assert.assertEquals(instructorResponseEntity.getBody().getUser(), instructorEntity.getUserEntity());
//    }

    public void instructorShouldByNotNull() {
        ResponseEntity<Instructor> instructorById = instructorService.getInstructorById(id, userEntity);
        Assert.assertNotNull(instructorById);

    }

    private Optional<SchoolEntity> prepareMockData() {
        return Optional.of(schoolEntity);
    }

    private Optional<InstructorEntity> prepareMockDataStudent() {
        InstructorEntity instructorEntity = new InstructorEntity();
        instructorEntity.setSchool(schoolEntity);
        instructorEntity.setUserEntity(userEntity);
        return Optional.of(instructorEntity);
    }

    private ResponseEntity<AuthBack> prepareMockDataAuth() {
        AuthBack authBack = new AuthBack();
        authBack.setUsername("Marek");
        authBack.setIdAuth(1L);
        return new ResponseEntity<>(authBack, HttpStatus.OK);
    }

    private User getUser() {
        User user = new User();
        user.setId(1L);
        user.setFirstName("Marek");
        user.setSecondName("Nowak");
        user.setEmail("pkvs@o2.pl");
        user.setPhoneNumber("+12 123123322");
        return user;
    }

    private Instructor getInstructor() {
        Instructor instructor = new Instructor();
        instructor.setId(1L);
        instructor.setSchoolId(1L);
        instructor.setUser(getUser());
        return instructor;
    }

    private Auth getAuth() {
        Auth auth = new Auth();
        auth.setRole("INSTRUCTOR");
        auth.setUsername("Marek");
        auth.setPassword("1234");
        return auth;
    }

    private InstructorAndAuth getInstructorAndAuth() {
        InstructorAndAuth instructorAndAuth = new InstructorAndAuth();
        instructorAndAuth.setAuth(getAuth());
        instructorAndAuth.setInstructor(getInstructor());
        return instructorAndAuth;
    }


}
