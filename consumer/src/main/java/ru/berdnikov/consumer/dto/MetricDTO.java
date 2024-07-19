package ru.berdnikov.consumer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author danilaberdnikov on MetricDTO.
 * @project consumer
 */
@Data
@Schema(name = "Метрика")
public class MetricDTO {
    @Schema(description = "Название метрики")
    private String metricName;

    @Schema(description = "Значения метрики")
    private List<MetricValueDTO> valueDTO;
}
