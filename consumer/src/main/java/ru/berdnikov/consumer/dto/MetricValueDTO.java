package ru.berdnikov.consumer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author danilaberdnikov on MetricValueDTO.
 * @project consumer
 */
@Data
@Schema(name = "Значение метрики")
public class MetricValueDTO {
    @Schema(description = "Значение метрики")
    private Double value;

    @Schema(description = "Время получения метрики")
    private LocalDateTime localDateTime;
}
