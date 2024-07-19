package ru.berdnikov.consumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.berdnikov.consumer.entity.Metric;

/**
 * @author danilaberdnikov on MetricRepository.
 * @project consumer
 */
@Repository
public interface MetricRepository extends JpaRepository<Metric, Long> {
    Metric findByName(String name);
}
