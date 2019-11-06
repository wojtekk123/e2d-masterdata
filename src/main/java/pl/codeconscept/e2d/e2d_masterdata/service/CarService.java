package pl.codeconscept.e2d.e2d_masterdata.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.codeconscept.e2d.e2d_masterdata.database.entity.CarEntity;
import pl.codeconscept.e2d.e2d_masterdata.database.entity.SchoolEntity;
import pl.codeconscept.e2d.e2d_masterdata.database.repository.CarRepo;
import pl.codeconscept.e2d.e2d_masterdata.database.repository.SchoolRepo;
import pl.codeconscept.e2d.e2d_masterdata.dto.Car;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepo carRepo;
    private final SchoolRepo schoolRepo;

    public CarEntity saveCar(Car carDto) {
        CarEntity car = mapObject(carDto);
        return carRepo.save(car);
    }

    public List<CarEntity> getAllCar() {
        return carRepo.findAll();
    }

    public CarEntity getCarById(Integer id) {
        return carRepo.findById(id.longValue()).get();
    }

    public void deleteCar(Integer id) {
        carRepo.delete(carRepo.findById(id.longValue()).get());
    }

    public CarEntity updateCar(Integer id, Car carDto) {
        CarEntity carEntity = mapObject(carDto);
        return carRepo.findById(id.longValue()).map(car -> {
            car.setSchoolId(carEntity.getSchoolId());
            car.setModel(carEntity.getModel());
            car.setBrand(carEntity.getBrand());
            car.setRegistrationNumber(carEntity.getRegistrationNumber());
            return carRepo.save(car);
        }).get();
    }

    private CarEntity mapObject(Car carDto) {
        SchoolEntity school = schoolRepo.findById(carDto.getSchoolId()).get();
        return new CarEntity(school, carDto.getModel(), carDto.getBrand(), carDto.getRegistrationNumber());
    }

}