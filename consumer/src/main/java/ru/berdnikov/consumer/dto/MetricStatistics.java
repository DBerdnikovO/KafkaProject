package ru.berdnikov.consumer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author danilaberdnikov on MetricStatistics.
 * @project consumer
 */
@Data
@Schema(name = "Статистика метрики")
@AllArgsConstructor
public class MetricStatistics {
    @Schema(description = "Минимальное значение")
    private double min;

    @Schema(description = "Максимальное значение")
    private double max;

    @Schema(description = "Среднее значение")
    private double average;
}
