package pl.codeconcept.e2d.e2dmasterdata.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class E2DMissingException extends IllegalArgumentException {

    private final String id;

    public E2DMissingException(String id) {
        super(String.format(" not found : '%s'", id));
        log.error("missing data");
        this.id = id;
    }

    public String getId() {
        return this.id;
    }


}


