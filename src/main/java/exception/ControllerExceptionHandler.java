package exception;

import dto.ErrorMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger LOG = LogManager.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    ErrorMessage unexpected(Exception e) {
        LOG.error("Unexpected exception {}", e.getMessage());
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

}
