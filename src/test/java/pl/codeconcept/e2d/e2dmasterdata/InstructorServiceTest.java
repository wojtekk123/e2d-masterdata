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
import org.springframework.http.ResponseEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.InstructorEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.SchoolEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.UserEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.enums.UserType;
import pl.codeconcept.e2d.e2dmasterdata.database.repository.InstructorRepo;
import pl.codeconcept.e2d.e2dmasterdata.database.repository.SchoolRepo;
import pl.codeconcept.e2d.e2dmasterdata.model.*;
import pl.codeconcept.e2d.e2dmasterdata.service.jwt.JwtAuthFilter;
import pl.codeconcept.e2d.e2dmasterdata.service.mappers.UserMapper;
import pl.codeconcept.e2d.e2dmasterdata.service.masterdata.InstructorService;
import pl.codeconcept.e2d.e2dmasterdata.service.template.TemplateRestQueries;

import javax.validation.Valid;
import java.rmi.ConnectIOException;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class InstructorServiceTest {


    @Mock
    SchoolRepo schoolRepo;

    @Mock
    InstructorRepo instructorRepo;

    @Mock
    TemplateRestQueries templateRestQueries;

    @Mock
    JwtAuthFilter jwtAuthFilter;

    @InjectMocks
    InstructorService instructorService;


    private final UserEntity userEntity = new UserEntity("Marek", 1L, "Nowak", UserType.ADMIN, "pkvs@o2.pl", "+12 123123322");
    private final SchoolEntity schoolEntity = new SchoolEntity(userEntity, "Szkoła Podstawowa", "Szkoła Podstawowa nr 9 im. Króla Jana III Sobieskiego");
    private final InstructorEntity instructorEntity = new InstructorEntity(schoolEntity, userEntity);
    private final InstructorAndAuth instructorAndAuth = getInstructorAndAuth();
    private final Long id = 1L;
    private final String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJlayIsInJvbGUiOiJST0xFX0FETUlOIiwiaWQiOjEsImlhdCI6MTU3NzEwNjMwMCwiZXhwIjoxNTc3MTE4MzAwfQ.Kw9hbXl3t_i64YwzGPbY8gPfMWCHy83d12gdu3HOksVKxxBrg2dz1ZULemSXGvGNLqMS7FXxExL-2oYm_q6fnA";

    @Before
    public void init() throws ConnectIOException {
        MockitoAnnotations.initMocks(this);
        BDDMockito.given(schoolRepo.findById(id)).willReturn(prepareMockData());
        BDDMockito.given(instructorRepo.findById(id)).willReturn(prepareMockDataStudent());
        BDDMockito.given(jwtAuthFilter.getToken()).willReturn(token);
        BDDMockito.given(templateRestQueries.getUserData(token, getInstructorAndAuth().getAuth(), UserType.INSTRUCTOR)).willReturn(prepareMockDataAuth());
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

    @Test
    public void shouldReturnInstructor() {
        ResponseEntity<Instructor> instructorResponseEntity = instructorService.saveInstructor(instructorAndAuth, userEntity);
        @Valid User user = instructorResponseEntity.getBody().getUser();
        Assert.assertEquals(UserMapper.mapToEntity(user, UserType.ADMIN, 1L), instructorEntity.getUserEntity());
    }

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

    private AuthBack prepareMockDataAuth() {
        AuthBack authBack = new AuthBack();
        authBack.setUsername("Marek");
        authBack.setIdAuth(1L);
        return authBack;
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
