package ru.berdnikov.producer.service;

import java.util.Map;

/**
 * @author danilaberdnikov on MetricsService.
 * @project producer
 */
public interface MetricsService {
    Map<String, Object> collectMetrics();
}
