package pl.codeconcept.e2d.e2dmasterdata.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class E2DAccessDenied extends IllegalArgumentException {

    private String id;

    public E2DAccessDenied(String id) {
        super(String.format("access denied to take action for: %s", id));
        this.id = id;
    }
    public String getId() {
        return this.id;
    }

}


