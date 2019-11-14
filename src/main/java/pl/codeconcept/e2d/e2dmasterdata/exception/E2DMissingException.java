package pl.codeconcept.e2d.e2dmasterdata.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class E2DMissingException extends RuntimeException {


    private String id;

    public E2DMissingException(String id) {
        super(String.format(" not found : '%s'", id));
        this.id = id;

    }

    public String getId() {
        return this.id;
    }


}


