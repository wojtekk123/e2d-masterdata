package pl.codeconscept.e2d.e2d_masterdata.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.codeconscept.e2d.e2d_masterdata.database.entity.CarEntity;
import pl.codeconscept.e2d.e2d_masterdata.service.CarService;
import pl.codeconscept.e2d.e2dmasterdata.api.CarApi;
import pl.codeconscept.e2d.e2d_masterdata.dto.Car;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CarApiImpl implements CarApi {

    private final CarService carService;

    @Override
    public ResponseEntity<CarEntity> createCar(@Valid Car body) {
        CarEntity carEntity = carService.saveCar(body);
        return new ResponseEntity<>(carEntity, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteCar(Integer id) {
        carService.deleteCar(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<CarEntity>> getAllCar() {
        List<CarEntity> allCar = carService.getAllCar();
        return new ResponseEntity<>(allCar,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CarEntity> getCar(Integer id) {
        CarEntity carById = carService.getCarById(id);
        return new ResponseEntity<>(carById,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CarEntity> updateCar(Integer id, @Valid Car body) {
        CarEntity carEntity = carService.updateCar(id, body);
        return new ResponseEntity<>(carEntity,HttpStatus.OK);
    }
}

