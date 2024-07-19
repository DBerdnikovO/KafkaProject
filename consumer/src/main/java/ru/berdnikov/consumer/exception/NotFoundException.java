package ru.berdnikov.consumer.exception;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author danilaberdnikov on NotFoundException.
 * @project consumer
 */
@Schema(description = "Исключение, указывающее на то, что запрашиваемый ресурс не найден.")
public class NotFoundException extends RuntimeException {
    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(Throwable cause) {
        super(cause);
    }
}

