package ru.berdnikov.consumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.berdnikov.consumer.entity.MetricValue;

/**
 * @author danilaberdnikov on MetricValueRepository.
 * @project consumer
 */
@Repository
public interface MetricValueRepository extends JpaRepository<MetricValue, Long> {
}
