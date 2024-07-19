package ru.berdnikov.consumer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.domain.Page;
import ru.berdnikov.consumer.dto.MetricDTO;
import ru.berdnikov.consumer.dto.MetricValueDTO;

import java.util.List;

/**
 * @author danilaberdnikov on MetricService.
 * @project consumer
 */
public interface MetricService {
    Page<MetricDTO> getAll(Integer from, Integer size);

    MetricDTO getMetricById(Long id);

    void saveMetric(String metrics) throws JsonProcessingException;

    List<MetricValueDTO> getMetricsByName(String name);
}
