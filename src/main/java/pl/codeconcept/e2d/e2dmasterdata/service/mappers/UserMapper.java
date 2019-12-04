package pl.codeconcept.e2d.e2dmasterdata.service.mappers;
import lombok.RequiredArgsConstructor;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.UserEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.enums.UserType;
import pl.codeconcept.e2d.e2dmasterdata.model.User;
import pl.codeconcept.e2d.e2dmasterdata.service.jwt.JwtAuthFilter;

@RequiredArgsConstructor
class UserMapper {


    static UserEntity mapToEntity(User user, String roleType){
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(user.getFirstName());
        userEntity.setSecondName(user.getSecondName());
        userEntity.setType(setTypeOfUser(roleType));
        userEntity.setEmail(user.getEmail());
        userEntity.setPhoneNumber(user.getPhoneNumber());
        return userEntity;

    }

    static User mapToModel(UserEntity userEntity){
        User user = new User();
        user.setId(userEntity.getId());
        user.setFirstName(userEntity.getFirstName());
        user.setSecondName(userEntity.getSecondName());
        user.setEmail(userEntity.getEmail());
        user.setPhoneNumber(userEntity.getPhoneNumber());
        return user;
    }

    static void mapToExistingEntity(UserEntity userEntity, User user) {
        userEntity.setFirstName(user.getFirstName());
        userEntity.setSecondName(user.getSecondName());
        userEntity.setEmail(user.getEmail());
        userEntity.setPhoneNumber(user.getPhoneNumber());

    }

    private static UserType setTypeOfUser(String type) {
        switch (type.toLowerCase()) {
            case "student":
                return UserType.STUDENT;
            case "school":
                return UserType.SCHOOL;
            case "admin":
                return UserType.ADMIN;
            case "instructor":
                return UserType.INSTRUCTOR;
            default:
                return null;
        }
    }

}