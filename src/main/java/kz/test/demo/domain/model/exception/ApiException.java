package kz.test.demo.domain.model.exception;


import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ApiException extends RuntimeException {
    private static final long serialVersionUID = -7056352146473732573L;
    private int exceptionCode;

    public ApiException(String message, int exceptionsCode, Throwable cause) {
        super(message, cause);
        this.exceptionCode = exceptionsCode;
    }

    public ApiException(String message, int exceptionsCode) {
        super(message);
        this.exceptionCode = exceptionsCode;
    }
}
