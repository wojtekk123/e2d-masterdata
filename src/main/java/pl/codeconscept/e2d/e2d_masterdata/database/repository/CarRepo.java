package pl.codeconscept.e2d.e2d_masterdata.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.codeconscept.e2d.e2d_masterdata.database.entity.CarEntity;

@Repository
public interface CarRepo extends JpaRepository<CarEntity,Long> {

}
