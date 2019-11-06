package pl.codeconscept.e2d.e2d_masterdata.dto;


import lombok.*;

import java.util.Date;

@Data
public class Student {

    private User userDto;
    private Long schoolId;
    private Date startEducationDate;
    private Date endEducationDate;

}
