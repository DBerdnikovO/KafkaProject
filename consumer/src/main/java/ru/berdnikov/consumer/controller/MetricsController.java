package ru.berdnikov.consumer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.berdnikov.consumer.dto.MetricDTO;
import ru.berdnikov.consumer.service.MetricService;

/**
 * @author danilaberdnikov on MetricsController.
 * @project consumer
 */
@RestController
@RequestMapping("/metrics")
@RequiredArgsConstructor
public class MetricsController {
    private final MetricService metricService;

    @GetMapping()
    @Operation(
            summary = "Получить лист метрик",
            description = "Позволяет вывести массив с метриками"
    )
    public ResponseEntity<Page<MetricDTO>> metricList(
            @RequestParam(name = "from", defaultValue = "0") @Parameter(description = "Номер страницы") Integer from,
            @RequestParam(name = "size", defaultValue = "10") @Parameter(description = "Размер страницы") Integer size) {
        Page<MetricDTO> metricDTOs = metricService.getAll(from, size);
        return ResponseEntity.ok().body(metricDTOs);
    }


    @GetMapping("/{id}")
    @Operation(
            summary = "Получить метрики по id",
            description = "Позволяет вывести метрики по их id"
    )
    public ResponseEntity<MetricDTO> getMetricById(@PathVariable Long id) {
        return ResponseEntity.ok().body(metricService.getMetricById(id));
    }
}
