package pl.codeconcept.e2d.e2dmasterdata.service.masterdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.CarEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.SchoolEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.UserEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.enums.UserType;
import pl.codeconcept.e2d.e2dmasterdata.database.repository.CarRepo;
import pl.codeconcept.e2d.e2dmasterdata.database.repository.InstructorRepo;
import pl.codeconcept.e2d.e2dmasterdata.database.repository.SchoolRepo;
import pl.codeconcept.e2d.e2dmasterdata.database.repository.StudentRepo;
import pl.codeconcept.e2d.e2dmasterdata.exception.E2DAccessDenied;
import pl.codeconcept.e2d.e2dmasterdata.exception.E2DMissingException;
import pl.codeconcept.e2d.e2dmasterdata.service.mappers.CarMapper;
import pl.codeconcept.e2d.e2dmasterdata.model.Car;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService extends AbstractMasterdataService {

    @Autowired
    private CarRepo carRepo;
    @Autowired
    private SchoolRepo schoolRepo;

    public CarService(SchoolRepo schoolRepo, InstructorRepo instructorRepo, StudentRepo studentRepo) {
        super(schoolRepo, instructorRepo, studentRepo);
    }

    public ResponseEntity<List<Car>> getAllCar(UserEntity userEntity) {

        List<CarEntity> all = carRepo.findAll();
        SchoolEntity schoolEntity = getSchoolEntityForUser(userEntity);

        try {
            if (userEntity.getType().equals(UserType.SCHOOL)) {
                List<CarEntity> filterCar = all.stream().filter(e -> e.getSchool().equals(schoolEntity)).collect(Collectors.toList());
                List<Car> cars = filterCar.stream().map(CarMapper::mapToModel).collect(Collectors.toList());
                return new ResponseEntity<>(cars, HttpStatus.OK);

            } else if (userEntity.getType().equals(UserType.ADMIN)) {
                List<Car> allCar = all.stream().map(CarMapper::mapToModel).collect(Collectors.toList());
                return new ResponseEntity<>(allCar, HttpStatus.OK);

            } else
                throw new RuntimeException();
        } catch (RuntimeException e) {
            throw new E2DAccessDenied("read-list");
        }
    }

    public ResponseEntity<Car> saveCar(Car car, UserEntity userEntity) {

        try {
            SchoolEntity schoolFromCar = schoolRepo.findById(car.getSchoolId()).orElseThrow(() -> new E2DMissingException("id-" + car.getId()));

            if (!userEntity.getType().equals(UserType.ADMIN)) {
                SchoolEntity schoolEntity = getSchoolEntityForUser(userEntity);

                if (schoolEntity == null || !(schoolEntity.getId().equals(schoolFromCar.getId())) || userEntity.getType().equals(UserType.STUDENT)) {
                    throw new RuntimeException();
                }
            }
            Car saveCar = CarMapper.mapToModel(carRepo.save(CarMapper.mapToEntity(car, schoolFromCar)));
            return new ResponseEntity<>(saveCar, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            throw new E2DMissingException("id- " + car.getId());
        } catch (RuntimeException e) {
            throw new E2DAccessDenied("create-car");
        }
    }

    public ResponseEntity<Car> getCarById(Long id, UserEntity userEntity) {

        try {
            CarEntity carEntity = carRepo.findById(id).orElseThrow(() -> new E2DMissingException("id-" + id));

            if (!userEntity.getType().equals(UserType.ADMIN)) {
                SchoolEntity schoolEntity = getSchoolEntityForUser(userEntity);

                if (schoolEntity == null || !(schoolEntity.getId().equals(carEntity.getSchool().getId()))) {
                    throw new RuntimeException();
                }
            }
            return new ResponseEntity<>(CarMapper.mapToModel(carEntity), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            throw new E2DMissingException("id- " + id);
        } catch (RuntimeException e) {
            throw new E2DAccessDenied("get-id:" + id);
        }
    }

    public ResponseEntity<Void> deleteCar(Long id, UserEntity userEntity) {

        try {
            CarEntity carEntity = carRepo.findById(id).orElseThrow(() -> new E2DMissingException("id-" + id));

            if (!userEntity.getType().equals(UserType.ADMIN)) {
                SchoolEntity schoolEntity = getSchoolEntityForUser(userEntity);

                if (schoolEntity == null || !(schoolEntity.getId().equals(carEntity.getSchool().getId()))) {
                    throw new RuntimeException();
                }
            }
            carRepo.delete(carRepo.findById(id).orElseThrow(() -> new E2DMissingException("id-" + id)));
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            throw new E2DMissingException("id- " + id);
        } catch (RuntimeException e) {
            throw new E2DAccessDenied("delete-id:" + id);
        }
    }

    public ResponseEntity<Car> updateCar(Long id, Car car, UserEntity userEntity) {

        try {
            CarEntity carEntity = carRepo.findById(id).orElseThrow(() -> new E2DMissingException("id-" + id));
            SchoolEntity schoolEntityToCheck = schoolRepo.findById(car.getSchoolId()).orElseThrow(() -> new E2DMissingException("id-" + id));

            if (!userEntity.getType().equals(UserType.ADMIN)) {
                SchoolEntity schoolEntity = getSchoolEntityForUser(userEntity);

                if (schoolEntity == null || !(schoolEntity.getId().equals(carEntity.getSchool().getId()))) {
                    throw new RuntimeException();
                }
            }
            CarMapper.mapToExistingEntity(carEntity, car, schoolEntityToCheck);
            return new ResponseEntity<>(CarMapper.mapToModel(carEntity), HttpStatus.OK);

        } catch (IllegalArgumentException e) {
            throw new E2DMissingException("id- " + id);
        } catch (RuntimeException e) {
            throw new E2DAccessDenied("update-id:" + id);
        }
    }
}