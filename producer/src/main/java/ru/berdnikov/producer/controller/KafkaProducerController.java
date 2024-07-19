package ru.berdnikov.producer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.berdnikov.producer.service.KafkaProducerService;

/**
 * @author danilaberdnikov on KafkaProducerController.
 * @project producer
 */
@RestController
@RequiredArgsConstructor
@Tag(name = "Metrics", description = "Взаимодействие с метриками")
public class KafkaProducerController {
    private final KafkaProducerService producerService;

    @PostMapping("/metrics")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(description = "Отправка метрик")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No content")})
    @Scheduled(fixedRate = 5000, initialDelay = 5000)
    public void sendMetrics() {
        producerService.sendMetrics();
    }
}
