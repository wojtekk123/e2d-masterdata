package pl.codeconcept.e2d.e2dmasterdata.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ResponseStatus(value = HttpStatus.CONFLICT)
public class E2DExistException extends RuntimeException {

    private final String id;

    public E2DExistException(String id) {
        super(String.format("access denied to take action for: %s", id));
        log.error("already exist");
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

}