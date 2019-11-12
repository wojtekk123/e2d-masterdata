package pl.codeconcept.e2d.e2dmasterdata.service.mappers;

import pl.codeconcept.e2d.e2dmasterdata.database.entity.CarEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.SchoolEntity;
import pl.codeconcept.e2d.e2dmasterdata.model.Car;

public class CarMapper {

    public static CarEntity mapToEntity(Car car, SchoolEntity schoolEntity) {
        CarEntity carEntity = new CarEntity();
        carEntity.setModel(car.getModel());
        carEntity.setBrand(car.getBrand());
        carEntity.setRegistrationNumber(car.getRegistrationNumber());
        carEntity.setSchool(schoolEntity);
        return carEntity;
    }

    public static void mapToExistingEntity(CarEntity carEntity, Car car, SchoolEntity schoolEntity) {
        carEntity.setModel(car.getModel());
        carEntity.setBrand(car.getBrand());
        carEntity.setSchool(schoolEntity);
        carEntity.setRegistrationNumber(car.getRegistrationNumber());
    }

    public static Car mapToModel(CarEntity carEntity) {
        Car car = new Car();
        car.setId(carEntity.getId());
        car.setModel(carEntity.getModel());
        car.setBrand(carEntity.getBrand());
        car.setRegistrationNumber(carEntity.getRegistrationNumber());
        car.setSchoolId(carEntity.getSchool().getId());
        return car;
    }
}
