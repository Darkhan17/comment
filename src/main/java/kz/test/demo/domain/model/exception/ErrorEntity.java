package kz.test.demo.domain.model.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorEntity {
    private HttpStatus status;
    private Class errorClass;
    private Enum exceptionType;
}
