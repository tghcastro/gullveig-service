package gullveig.companiesservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.MessageFormat;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class SectorNotFoundException extends RuntimeException {
    public SectorNotFoundException(Long id) {
        super(MessageFormat.format("Sector not found [id:{0}]", id));
    }
}
