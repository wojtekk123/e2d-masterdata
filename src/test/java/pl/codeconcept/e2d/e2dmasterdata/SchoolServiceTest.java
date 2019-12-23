package pl.codeconcept.e2d.e2dmasterdata;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.SchoolEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.UserEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.enums.UserType;
import pl.codeconcept.e2d.e2dmasterdata.database.repository.SchoolRepo;
import pl.codeconcept.e2d.e2dmasterdata.exception.E2DAccessDenied;
import pl.codeconcept.e2d.e2dmasterdata.service.masterdata.SchoolService;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class SchoolServiceTest {

    @Mock
    SchoolRepo schoolRepo;

    @InjectMocks
    SchoolService schoolService;

    private final UserEntity userEntity = new UserEntity("Marek", 1L, "Nowak", UserType.INSTRUCTOR, "pkvs@o2.pl", "+12 123123322");
    private final SchoolEntity schoolEntity = new SchoolEntity(userEntity, "Szkoła Podstawowa", "Szkoła Podstawowa nr 9 im. Króla Jana III Sobieskiego");
    private final Long id = 1L;

    @Before
    public void init() {
        BDDMockito.given(schoolRepo.findById(id)).willReturn(prepareMockData());
    }

    @Test
    public void schoolShouldByNotNull() {
        Optional<SchoolEntity> byId = schoolRepo.findById(id);
        Assert.assertNotNull(byId.get());
    }

    @Test(expected = E2DAccessDenied.class)
    public void whenNoAccessthrowException() {
    schoolService.getSchoolById(id,userEntity);
        }

    private Optional<SchoolEntity> prepareMockData() {
        return Optional.of(schoolEntity);
    }

}