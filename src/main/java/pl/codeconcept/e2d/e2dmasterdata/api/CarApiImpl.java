package pl.codeconcept.e2d.e2dmasterdata.api;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.CarEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.UserEntity;
import pl.codeconcept.e2d.e2dmasterdata.exception.E2DAccessDenied;
import pl.codeconcept.e2d.e2dmasterdata.exception.E2DMissingException;
import pl.codeconcept.e2d.e2dmasterdata.service.mappers.CarMapper;
import pl.codeconcept.e2d.e2dmasterdata.service.masterdata.CarService;
import pl.codeconcept.e2d.e2dmasterdata.model.Car;
import pl.codeconcept.e2d.e2dmasterdata.service.privilege.PrivilegeService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequiredArgsConstructor
public class CarApiImpl extends AbstractApi implements CarApi {

    private final CarService carService;

    @Override
    public ResponseEntity<Car> createCar(@Valid Car body) {
        UserEntity userEntity = getUserFromToken();
      return carService.saveCar(body,userEntity);
    }

    @Override
    public ResponseEntity<List<Car>> getAllCar() {
        UserEntity userEntity = getUserFromToken();
        return carService.getAllCar(userEntity);
    }

    @Override
    public ResponseEntity<Void> deleteCar(Long id) {
        UserEntity userEntity = getUserFromToken();
        return carService.deleteCar(id,userEntity);
    }

    @Override
    public ResponseEntity<Car> getCar(Long id) {
        UserEntity userEntity = getUserFromToken();
        return carService.getCarById(id,userEntity);
    }

    @Override
    public ResponseEntity<Car> updateCar(Long id, @Valid Car body) {
        UserEntity userEntity = getUserFromToken();
        return carService.updateCar(id,body,userEntity);
    }
}
