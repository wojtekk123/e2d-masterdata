package pl.codeconcept.e2d.e2dmasterdata;

import org.junit.Assert;
import org.junit.Test;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.SchoolEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.StudentEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.entity.UserEntity;
import pl.codeconcept.e2d.e2dmasterdata.database.enums.UserType;
import pl.codeconcept.e2d.e2dmasterdata.model.Student;
import pl.codeconcept.e2d.e2dmasterdata.model.User;
import pl.codeconcept.e2d.e2dmasterdata.service.mappers.StudentMapper;
import java.util.Date;

public class StudentMapperTest {

    private Date startDate  = new Date(2013-10-24);
    private Date endDate  = new Date(2013-10-24);
    private UserEntity userEntity = new UserEntity("Marek",1L,"Nowak", UserType.INSTRUCTOR,"pkvs@o2.pl","+12 123123322");
    private SchoolEntity schoolEntity = new SchoolEntity(userEntity, "Szkoła Podstawowa", "Szkoła Podstawowa nr 9 im. Króla Jana III Sobieskiego");
    private StudentEntity studentEntity = new StudentEntity(userEntity,schoolEntity,startDate,endDate);

    @Test
    public void wheneEqualsThenCorrent () {

        StudentEntity studentEntity = StudentMapper.mapToEntity(getStudent(), schoolEntity, UserType.INSTRUCTOR, 1L);

        Assert.assertEquals(studentEntity,this.studentEntity);
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

    private Student getStudent () {
        Student student = new Student();
        student.setUser(getUser());
        student.setSchoolId(2L);
        student.setEndEducation(endDate);
        student.setStartEducation(startDate);
        return student;
    }

}
