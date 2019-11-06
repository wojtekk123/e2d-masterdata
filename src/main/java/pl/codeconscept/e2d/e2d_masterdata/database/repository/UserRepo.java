package pl.codeconscept.e2d.e2d_masterdata.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.codeconscept.e2d.e2d_masterdata.database.entity.UserEntity;

@Repository
public interface UserRepo extends JpaRepository<UserEntity,Long> {

    public UserEntity findByInstructorId (Long id);
    public UserEntity findByStudentId (Long id);
    public boolean existsByEmail(String emial);
    public boolean existsByPhoneNumber(String phoneNumber);

}
