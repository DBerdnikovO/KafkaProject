package ru.berdnikov.producer.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import ru.berdnikov.producer.service.ErrorLogService;
import ru.berdnikov.producer.service.KafkaProducerService;
import ru.berdnikov.producer.service.MetricsService;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @author danilaberdnikov on KafkaProducerServiceImpl.
 * @project producer
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducerServiceImpl implements KafkaProducerService {
    private final MetricsService metricsService;
    private final ErrorLogService errorLogService;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Value("${topic.name}")
    private String topicName;

    @Override
    public void sendMetrics() {
        Map<String, Object> metrics;
        try {
            metrics = metricsService.collectMetrics();
        } catch (Exception e) {
            handleFailure("Failed to collect metrics", e);
            return;
        }

        String metricsJson;
        try {
            metricsJson = objectMapper.writeValueAsString(metrics);
        } catch (JsonProcessingException e) {
            handleFailure("Failed to serialize metrics to JSON", e);
            return;
        }

        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, metricsJson);

        future.whenComplete((result, ex) -> {
            if (ex != null) {
                handleFailure("Failed to send metrics to Kafka", ex);
            } else {
                log.info("Successfully sent metrics=[{}] with offset=[{}]", metricsJson, result.getRecordMetadata().offset());
            }
        });
    }

    private void handleFailure(String message, Throwable e) {
        errorLogService.addErrorLog(message);
        log.error(message, e);
    }
}
