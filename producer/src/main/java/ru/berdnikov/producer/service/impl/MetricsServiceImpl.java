package ru.berdnikov.producer.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.berdnikov.producer.service.ErrorLogService;
import ru.berdnikov.producer.service.MetricsService;

import java.lang.management.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author danilaberdnikov on MetricsServiceImpl.
 * @project producer
 */
@Service
@RequiredArgsConstructor
public class MetricsServiceImpl implements MetricsService {
    private final ErrorLogService errorLogService;

    @Override
    public Map<String, Object> collectMetrics() {

        Map<String, Object> metrics = new HashMap<>();

        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
        MemoryUsage nonHeapMemoryUsage = memoryMXBean.getNonHeapMemoryUsage();
        metrics.put("heapMemoryUsage", heapMemoryUsage.getUsed());
        metrics.put("nonHeapMemoryUsage", nonHeapMemoryUsage.getUsed());

        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        metrics.put("threadCount", threadMXBean.getThreadCount());

        long gcTime = ManagementFactory.getGarbageCollectorMXBeans().stream()
                .mapToLong(GarbageCollectorMXBean::getCollectionTime)
                .sum();
        metrics.put("gcTime", gcTime);

        long uptime = ManagementFactory.getRuntimeMXBean().getUptime();
        metrics.put("uptime", uptime);

        metrics.put("errorCount", errorLogService.count());

        return metrics;
    }
}
