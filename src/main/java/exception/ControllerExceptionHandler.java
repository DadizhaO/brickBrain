package exception;

import dto.ErrorMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger LOG = LogManager.getLogger(ControllerExceptionHandler.class);

    private Map<String, String> validationCodeDescription = new HashMap<>();

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    ErrorMessage unexpected(Exception e) {
        LOG.error("Unexpected exception {}", e.getMessage());
        e.printStackTrace();
        return new ErrorMessage(e.getMessage());
    }

    @ExceptionHandler(value = {DeleteException.class, UpdateException.class})
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public @ResponseBody
    ErrorMessage entityExistingProblem(Exception e) {
        LOG.warn("Unprocessable entity {}", e.getMessage());
        return new ErrorMessage(e.getMessage());
    }

    @ExceptionHandler(AbsentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody
    ErrorMessage entityIsAbsent(Exception e) {
        LOG.warn("Entity not found");
        return new ErrorMessage(e.getMessage());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ErrorMessage validationProblem(MethodArgumentNotValidException e) {
        LOG.warn("Request validation problem {}", e.getMessage());
        FieldError fieldError = e.getBindingResult().getFieldError();
        return new ErrorMessage(validationCodeDescription.get(fieldError.getDefaultMessage()));
    }

    @PostConstruct
    private void intValidationCodeDescription() {
        validationCodeDescription.put("1", "field officeId is required and must be bigger than 0");
        validationCodeDescription.put("2", "field city is required and accepts only letters, spaces and dashes(-)");
        validationCodeDescription.put("3", "field sales is required and must be bigger than 0");
        validationCodeDescription.put("4", "field region is required and accepts only letters and size must be between 1..15");
        validationCodeDescription.put("5", "field target must bigger than 50");
    }

}
