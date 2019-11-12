package pl.codeconcept.e2d.e2dmasterdata.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.CarEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.SchoolEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.repository.CarRepo;
import pl.codeconcept.e2d.e2dmasterdata.database.repository.SchoolRepo;
import pl.codeconcept.e2d.e2dmasterdata.service.mappers.CarMapper;
import pl.codeconcept.e2d.e2dmasterdata.model.Car;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepo carRepo;
    private final SchoolRepo schoolRepo;

    public Car saveCar(Car car) {
        SchoolEntity schoolEntity = schoolRepo.findById(car.getSchoolId()).orElseThrow(() -> new IllegalArgumentException("School does not exist."));
        CarEntity savedCar = carRepo.save(CarMapper.mapToEntity(car, schoolEntity));
        return CarMapper.mapToModel(savedCar);
    }

    public List<Car> getAllCar() {
        List<CarEntity> all = carRepo.findAll();
        return all.stream().map(CarMapper::mapToModel).collect(Collectors.toList());
    }

    public Car getCarById(Long id) {
        CarEntity carEntity = carRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Car does not exist."));
        return CarMapper.mapToModel(carEntity);
    }

    public void deleteCar(Long id) {
        carRepo.delete(carRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Car does not exist.")));
    }

    public Car updateCar(Long id, Car car) {
        SchoolEntity schoolEntity = schoolRepo.findById(car.getSchoolId()).orElseThrow(() -> new IllegalArgumentException("School does not exist."));
        CarEntity carEntity = carRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("car does not exist"));
        CarMapper.mapToExistingEntity(carEntity, car, schoolEntity);
        return CarMapper.mapToModel(carEntity);
    }
}