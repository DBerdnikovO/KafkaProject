package ru.berdnikov.consumer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.berdnikov.consumer.dto.MetricStatistics;
import ru.berdnikov.consumer.dto.MetricValueDTO;

import java.util.List;

/**
 * @author danilaberdnikov on AnalysisServiceImpl.
 * @project consumer
 */
@Service
@RequiredArgsConstructor
public class AnalysisServiceImpl implements AnalysisService {
    private final MetricService metricService;

    @Override
    public double calculateAverageMetricValue(String metricName) {
        List<MetricValueDTO> metricValues = metricService.getMetricsByName(metricName);

        double sum = metricValues.stream()
                .mapToDouble(MetricValueDTO::getValue)
                .sum();
        return sum / metricValues.size();
    }

    @Override
    public double calculateMinMetricValue(String metricName) {
        List<MetricValueDTO> metricValues = metricService.getMetricsByName(metricName);

        return metricValues.stream()
                .mapToDouble(MetricValueDTO::getValue)
                .min()
                .orElse(0.0);
    }

    @Override
    public double calculateMaxMetricValue(String metricName) {
        List<MetricValueDTO> metricValues = metricService.getMetricsByName(metricName);

        return metricValues.stream()
                .mapToDouble(MetricValueDTO::getValue)
                .max()
                .orElse(0.0);
    }

    @Override
    public MetricStatistics calculateMetricStatistics(String metricName) {
        List<MetricValueDTO> metricValues = metricService.getMetricsByName(metricName);

        double min = metricValues.stream()
                .mapToDouble(MetricValueDTO::getValue)
                .min()
                .orElse(0.0);

        double max = metricValues.stream()
                .mapToDouble(MetricValueDTO::getValue)
                .max()
                .orElse(0.0);

        double average = metricValues.stream()
                .mapToDouble(MetricValueDTO::getValue)
                .average()
                .orElse(0.0);

        return new MetricStatistics(min, max, average);
    }
}
