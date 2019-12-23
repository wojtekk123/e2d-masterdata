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
import pl.codeconcept.e2d.e2dmasterdata.database.entity.CarEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.SchoolEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.UserEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.enums.UserType;
import pl.codeconcept.e2d.e2dmasterdata.database.repository.CarRepo;
import pl.codeconcept.e2d.e2dmasterdata.service.masterdata.CarService;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceTest {

    @Mock
    CarRepo carRepo;

    @InjectMocks
    CarService carService;

    private final UserEntity userEntity = new UserEntity("Marek", 1L, "Nowak", UserType.ADMIN, "pkvs@o2.pl", "+12 123123322");
    private final SchoolEntity schoolEntity = new SchoolEntity(userEntity, "Szkoła Podstawowa", "Szkoła Podstawowa nr 9 im. Króla Jana III Sobieskiego");
    private final Long id = 1L;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        BDDMockito.given(carRepo.findById(id)).willReturn(prepareMockDataGetCar());
    }

    @Test
    public void ShouldByNotNull() {
        Optional<CarEntity> byId = carRepo.findById(id);
        Assert.assertNotNull(byId.get());
    }

//    @Test(expected = E2DAccessDenied.class)
//    public void whenNoAccessThrowException() {
//        carService.getCarById(id, userEntity);
//    }
//



    private Optional<CarEntity> prepareMockDataGetCar() {
        CarEntity carEntity = new CarEntity(schoolEntity,"FIAT","500","ABC123");
        return Optional.of(carEntity);
    }

}
