package ru.berdnikov.consumer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.retrytopic.TopicSuffixingStrategy;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

/**
 * @author danilaberdnikov on KafkaConsumerService.
 * @project consumer
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumerService {
    private final KafkaTemplate<Object, Object> kafkaTemplate;

    private final String TOPIC_NAME = "metrics-topic";
    private final String TOPIC_NAME_DTL = "metrics-topic-dlt";

    private final MetricService metricService;

    @RetryableTopic(
            attempts = "4",
            backoff = @Backoff(delay = 1000, multiplier = 2.0),
            autoCreateTopics = "false",
            topicSuffixingStrategy = TopicSuffixingStrategy.SUFFIX_WITH_INDEX_VALUE)
    @KafkaListener(groupId = "metricsGroup", topics = TOPIC_NAME)
    public void listen(String message, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic, @Header(KafkaHeaders.OFFSET) long offset) {
        try {
            metricService.saveMetric(message);
            log.info("Received message: " + message);
        } catch (DataIntegrityViolationException | JsonProcessingException exc) {
            log.error("Error processing message: {}, topic: {}, offset: {}", message, topic, offset, exc);
            kafkaTemplate.send(TOPIC_NAME_DTL, message);
        }
    }

    @DltHandler
    public void dlt(String in, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        log.info(in + " from " + topic);
    }
}
