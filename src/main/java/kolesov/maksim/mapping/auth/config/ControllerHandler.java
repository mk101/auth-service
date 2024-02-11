package kolesov.maksim.mapping.auth.config;

import kolesov.maksim.mapping.auth.dto.ResponseDto;
import kolesov.maksim.mapping.auth.exception.ServiceException;
import kolesov.maksim.mapping.auth.exception.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ControllerHandler {

    private static final String LOG_MESSAGE = "Handle exception";

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto<Void>> handleMethodArgumentNotValidException(MethodArgumentNotValidException  e) {
        log.error(LOG_MESSAGE, e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ResponseDto<>(false, null, e.getBody().getDetail()));
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    ResponseEntity<ResponseDto<Void>> handleAuth(UnauthorizedException e) {
        log.error(LOG_MESSAGE, e);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ResponseDto<>(false, null, e.getMessage()));
    }

    @ExceptionHandler(value = ServiceException.class)
    ResponseEntity<ResponseDto<Void>> handleService(ServiceException e) {
        log.error(LOG_MESSAGE, e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ResponseDto<>(false, null, e.getMessage()));
    }

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ResponseDto<Void>> handleException(Exception e) {
        log.error(LOG_MESSAGE, e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseDto<>(false, null, "Internal service exception"));
    }

}
