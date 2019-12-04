package pl.codeconcept.e2d.e2dmasterdata.service.mappers;

import pl.codeconcept.e2d.e2dmasterdata.database.entity.AuthEntity;
import pl.codeconcept.e2d.e2dmasterdata.model.StudentAndAuth;

public class AuthMapper {

    public static AuthEntity mapToEntity(StudentAndAuth studentAndAuth) {
        AuthEntity authEntity = new AuthEntity();
        authEntity.setUserEntity(UserMapper.mapToEntity(studentAndAuth.getStudent().getUser(),studentAndAuth.getStudent().getUser().getType()));
        authEntity.setUsername(studentAndAuth.getAuth().getUsername());
        authEntity.setPassword(studentAndAuth.getAuth().getPassword());
        return authEntity;
    }


}
