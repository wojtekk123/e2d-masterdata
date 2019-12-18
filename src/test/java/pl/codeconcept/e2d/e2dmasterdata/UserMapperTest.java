package pl.codeconcept.e2d.e2dmasterdata;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.UserEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.enums.UserType;
import pl.codeconcept.e2d.e2dmasterdata.model.User;
import pl.codeconcept.e2d.e2dmasterdata.service.mappers.UserMapper;

@RunWith(MockitoJUnitRunner.class)
public class UserMapperTest {


    private UserEntity userEntity = new UserEntity("Marek",1L,"Nowak", UserType.ADMIN,"pkvs@o2.pl","+12 123123322");

    @Test
    public void wheneEqualsThenCorrent () {

        UserEntity userEntity = UserMapper.mapToEntity(getUser(), UserType.ADMIN, 1L);
        Assert.assertEquals(userEntity,this.userEntity);

    }

    private User getUser () {
        User user = new User();
        user.setId(1L);
        user.setFirstName("Marek");
        user.setSecondName("Nowak");
        user.setEmail("pkvs@o2.pl");
        user.setPhoneNumber("+12 123123322");
        return user;
    }
}
