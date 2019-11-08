package pl.codeconscept.e2d.e2d_masterdata.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.codeconscept.e2d.e2d_masterdata.database.entity.CarEntity;
import pl.codeconscept.e2d.e2d_masterdata.database.entity.SchoolEntity;
import pl.codeconscept.e2d.e2d_masterdata.database.repository.CarRepo;
import pl.codeconscept.e2d.e2d_masterdata.database.repository.SchoolRepo;
import pl.codeconscept.e2d.e2d_masterdata.service.mappers.CarMapper;
import pl.codeconscept.e2d.e2dmasterdata.model.Car;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepo carRepo;
    private final SchoolRepo schoolRepo;

    public Car saveCar(Car car) {
        SchoolEntity schoolEntity = schoolRepo.findById(car.getSchoolId()).orElseThrow(IllegalArgumentException::new);
        CarEntity savedCar = carRepo.save(CarMapper.mapToEntity(car, schoolEntity));
        return CarMapper.mapToModel(savedCar);
    }

    public List<Car> getAllCar() {
        List<CarEntity> all = carRepo.findAll();
        return all.stream().map(CarMapper::mapToModel).collect(Collectors.toList());
    }

    public Car getCarById(Long id) {
        CarEntity carEntity = carRepo.findById(id).orElseThrow(IllegalArgumentException::new);
        return CarMapper.mapToModel(carEntity);
    }

    public void deleteCar(Long id) {
        carRepo.delete(carRepo.findById(id).orElseThrow(IllegalArgumentException::new));
    }

    public Car updateCar(Long id, Car carDto) {
        SchoolEntity schoolEntity = schoolRepo.findById(carDto.getSchoolId()).orElseThrow(IllegalArgumentException::new);
        CarEntity carEntity = CarMapper.mapToEntity(carDto, schoolEntity);
        CarEntity updatedCar = carRepo.findById(id).map(c -> {
            c.setSchool(carEntity.getSchool());
            c.setModel(carEntity.getModel());
            c.setBrand(carEntity.getBrand());
            c.setRegistrationNumber(carEntity.getRegistrationNumber());
            return carRepo.save(c);
        }).orElseThrow(IllegalArgumentException::new);
        return CarMapper.mapToModel(updatedCar);
    }
}