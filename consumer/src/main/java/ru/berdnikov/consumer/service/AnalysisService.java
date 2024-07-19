package ru.berdnikov.consumer.service;

import ru.berdnikov.consumer.dto.MetricStatistics;

/**
 * @author danilaberdnikov on AnalysisService.
 * @project consumer
 */
public interface AnalysisService {

    double calculateAverageMetricValue(String metricName);

    double calculateMinMetricValue(String metricName);

    double calculateMaxMetricValue(String metricName);

    MetricStatistics calculateMetricStatistics(String metricName);
}
