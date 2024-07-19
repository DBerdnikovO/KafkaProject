package ru.berdnikov.consumer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.berdnikov.consumer.dto.MetricDTO;
import ru.berdnikov.consumer.dto.MetricValueDTO;
import ru.berdnikov.consumer.entity.Metric;
import ru.berdnikov.consumer.entity.MetricValue;
import ru.berdnikov.consumer.exception.NotFoundException;
import ru.berdnikov.consumer.mapper.MetricMapper;
import ru.berdnikov.consumer.mapper.MetricValueMapper;
import ru.berdnikov.consumer.repository.MetricRepository;
import ru.berdnikov.consumer.repository.MetricValueRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author danilaberdnikov on MetricServiceImpl.
 * @project consumer
 */
@Service
@Transactional
@RequiredArgsConstructor
public class MetricServiceImpl implements MetricService {
    private final MetricValueRepository metricValueRepository;
    private final MetricRepository metricRepository;
    private final MetricMapper metricMapper;
    private final MetricValueMapper metricValueMapper;
    private final ObjectMapper objectMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<MetricDTO> getAll(Integer from, Integer size) {
        Pageable pageable = PageRequest.of(from, size);
        Page<Metric> metricPage = metricRepository.findAll(pageable);
        return metricPage.map(metricMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public MetricDTO getMetricById(Long id) {
        Metric metric = findMetricById(id);
        return metricMapper.toDto(metric);
    }

    @Override
    public void saveMetric(String metricsJson) throws JsonProcessingException {
        Map<String, Object> metrics = objectMapper.readValue(metricsJson, Map.class);
        metrics.forEach((name, value) -> {
            Metric metric = metricRepository.findByName(name);

            if (metric == null) {
                metric = new Metric();
                metric.setName(name);
                metricRepository.save(metric);
            }

            MetricValue metricValue = new MetricValue();
            metricValue.setMetric(metric);
            metricValue.setValue(Double.valueOf(value.toString()));
            metricValue.setLocalDateTime(LocalDateTime.now());
            metricValueRepository.save(metricValue);
        });
    }

    @Override
    @Transactional(readOnly = true)
    public List<MetricValueDTO> getMetricsByName(String name) {
        Metric metric = findMetricByName(name);
        if (metric == null) {
            throw new NotFoundException("Metric with name " + name + " not found");
        }
        List<MetricValue> metricValues = metric.getMetricValues();
        return metricValues.stream().map(metricValueMapper::toDto).collect(Collectors.toList());
    }

    private Metric findMetricById(Long id) {
        return metricRepository.findById(id).orElseThrow(() -> new NotFoundException("Metric not found with id: " + id));
    }

    private Metric findMetricByName(String name) {
        return metricRepository.findByName(name);
    }

}
