package pl.codeconcept.e2d.e2dmasterdata.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.codeconcept.e2d.e2dmasterdata.service.CarService;
import pl.codeconcept.e2d.e2dmasterdata.model.Car;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CarApiImpl implements CarApi {

    private final CarService carService;

    @Override
    public ResponseEntity<Car> createCar(@Valid Car body) {
        return new ResponseEntity<>(carService.saveCar(body), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteCar(Long id) {
        carService.deleteCar(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<Car>> getAllCar() {
        return new ResponseEntity<>(carService.getAllCar(),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Car> getCar(Long id) {
        return new ResponseEntity<>(carService.getCarById(id),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Car> updateCar(Long id, @Valid Car body) {
        return new ResponseEntity<>(carService.updateCar(id,body),HttpStatus.OK);
    }
}

