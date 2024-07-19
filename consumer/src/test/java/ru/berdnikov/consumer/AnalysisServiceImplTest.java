package ru.berdnikov.consumer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.berdnikov.consumer.dto.MetricStatistics;
import ru.berdnikov.consumer.dto.MetricValueDTO;
import ru.berdnikov.consumer.service.AnalysisServiceImpl;
import ru.berdnikov.consumer.service.MetricService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @author danilaberdnikov on AnalysisServiceImplTest.
 * @project consumer
 */
class AnalysisServiceImplTest {

    @Mock
    private MetricService metricService;

    @InjectMocks
    private AnalysisServiceImpl analysisService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCalculateAverageMetricValue() {
        // Mock data
        String metricName = "memoryUsage";
        List<MetricValueDTO> metricValues = Arrays.asList(
                createMetricValue(100.0),
                createMetricValue(200.0),
                createMetricValue(300.0)
        );
        when(metricService.getMetricsByName(metricName)).thenReturn(metricValues);

        double average = analysisService.calculateAverageMetricValue(metricName);

        assertEquals(200.0, average, 0.01);
        verify(metricService).getMetricsByName(metricName);
    }

    @Test
    void testCalculateMinMetricValue() {
        // Mock data
        String metricName = "responseTime";
        List<MetricValueDTO> metricValues = Arrays.asList(
                createMetricValue(50.0),
                createMetricValue(30.0),
                createMetricValue(70.0)
        );
        when(metricService.getMetricsByName(metricName)).thenReturn(metricValues);

        double minValue = analysisService.calculateMinMetricValue(metricName);

        assertEquals(30.0, minValue, 0.01);
        verify(metricService).getMetricsByName(metricName);
    }

    @Test
    void testCalculateMaxMetricValue() {
        String metricName = "responseTime";
        List<MetricValueDTO> metricValues = Arrays.asList(
                createMetricValue(50.0),
                createMetricValue(30.0),
                createMetricValue(70.0)
        );
        when(metricService.getMetricsByName(metricName)).thenReturn(metricValues);

        double maxValue = analysisService.calculateMaxMetricValue(metricName);

        assertEquals(70.0, maxValue, 0.01);
        verify(metricService).getMetricsByName(metricName);
    }

    @Test
    void testCalculateMetricStatistics() {
        // Mock data
        String metricName = "responseTime";
        List<MetricValueDTO> metricValues = Arrays.asList(
                createMetricValue(50.0),
                createMetricValue(30.0),
                createMetricValue(70.0)
        );
        when(metricService.getMetricsByName(metricName)).thenReturn(metricValues);

        MetricStatistics statistics = analysisService.calculateMetricStatistics(metricName);

        assertEquals(30.0, statistics.getMin(), 0.01);
        assertEquals(70.0, statistics.getMax(), 0.01);
        assertEquals(50.0, statistics.getAverage(), 0.01);

        verify(metricService, times(1)).getMetricsByName(metricName);
    }


    private MetricValueDTO createMetricValue(Double value) {
        MetricValueDTO dto = new MetricValueDTO();
        dto.setValue(value);
        dto.setLocalDateTime(LocalDateTime.now());
        return dto;
    }
}