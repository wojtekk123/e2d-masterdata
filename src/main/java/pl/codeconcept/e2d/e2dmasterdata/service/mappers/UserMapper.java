package pl.codeconcept.e2d.e2dmasterdata.service.mappers;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.UserEntity;
import pl.codeconcept.e2d.e2dmasterdata.model.User;

class UserMapper {

    public static UserEntity mapToEntity (User user){
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(user.getFirstName());
        userEntity.setSecondName(user.getSecondName());
        userEntity.setEmail(user.getEmail());
        userEntity.setPhoneNumber(user.getPhoneNumber());
        return userEntity;

    }

    public static User mapToModel (UserEntity userEntity){
        User user = new User();
        user.setId(userEntity.getId());
        user.setFirstName(userEntity.getFirstName());
        user.setSecondName(userEntity.getSecondName());
        user.setEmail(userEntity.getEmail());
        user.setPhoneNumber(userEntity.getPhoneNumber());
        return user;
    }

    public static void mapToExistingEntity(UserEntity userEntity, User user) {
        userEntity.setFirstName(user.getFirstName());
        userEntity.setSecondName(user.getSecondName());
        userEntity.setEmail(user.getEmail());
        userEntity.setPhoneNumber(user.getPhoneNumber());

    }
}