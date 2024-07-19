package ru.berdnikov.producer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.berdnikov.producer.model.ErrorLog;

/**
 * @author danilaberdnikov on ErrorLogRepository.
 * @project producer
 */
@Repository
public interface ErrorLogRepository extends JpaRepository<ErrorLog, Long> {
    long count();
}
