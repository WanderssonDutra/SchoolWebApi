package Project.Web.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DataBaseConflictException extends RuntimeException{

    public DataBaseConflictException(String message){
        super(message);}
}
