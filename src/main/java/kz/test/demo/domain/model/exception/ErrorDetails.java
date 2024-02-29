package kz.test.demo.domain.model.exception;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ErrorDetails {
    int errorCode;
    String message;
    String url;
}
