package pl.codeconscept.e2d.e2d_masterdata.dto;

import lombok.Data;

@Data
public class Instructor {

    private User userDto;
    private Long schoolId ;

}
