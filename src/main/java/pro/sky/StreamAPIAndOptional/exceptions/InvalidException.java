package pro.sky.StreamAPIAndOptional.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpStatusCodeException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidException extends HttpStatusCodeException {

    public InvalidException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}

