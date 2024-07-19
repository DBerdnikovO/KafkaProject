package ru.berdnikov.consumer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.berdnikov.consumer.dto.MetricStatistics;
import ru.berdnikov.consumer.service.AnalysisService;

/**
 * @author danilaberdnikov on AnalysisController.
 * @project consumer
 */
@RestController
@RequestMapping("/analysis")
@RequiredArgsConstructor
@Tag(name = "Analysis", description = "API для анализа метрик")
public class AnalysisController {
    private final AnalysisService analysisService;

    @GetMapping("/average")
    @Operation(
            summary = "Получить cреднее значение",
            description = "Позволяет вывести среднее значение матрики по имени метрики"
    )
    public ResponseEntity<Double> getAverageMetricValueByName(
            @RequestParam(name = "metricName") @Parameter(description = "Название метрики") String metricName) {
        return ResponseEntity.ok().body(analysisService.calculateAverageMetricValue(metricName));
    }

    @GetMapping("/min")
    @Operation(
            summary = "Получить минимальное значение",
            description = "Позволяет вывести минимальное значение матрики по имени метрики"
    )
    public ResponseEntity<Double> getMinMetricValueByName(
            @RequestParam(name = "metricName") @Parameter(description = "Название метрики") String metricName) {
        return ResponseEntity.ok().body(analysisService.calculateMinMetricValue(metricName));
    }

    @GetMapping("/max")
    @Operation(
            summary = "Получить максимальное значение",
            description = "Позволяет вывести максимальное значение матрики по имени метрики"
    )
    public ResponseEntity<Double> getMaxMetricValueByName(
            @RequestParam(name = "metricName") @Parameter(description = "Название метрики") String metricName) {
        return ResponseEntity.ok().body(analysisService.calculateMaxMetricValue(metricName));
    }

    @GetMapping("/stat")
    @Operation(
            summary = "Получить статистику по имени метрики",
            description = "Позволяет вывести статистику матрики по имени метрики"
    )
    public ResponseEntity<MetricStatistics> getStatMetricValueByName(
            @RequestParam(name = "metricName") @Parameter(description = "Название метрики") String metricName) {
        return ResponseEntity.ok().body(analysisService.calculateMetricStatistics(metricName));
    }
}
