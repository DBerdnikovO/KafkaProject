package ru.berdnikov.producer.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.berdnikov.producer.model.ErrorLog;
import ru.berdnikov.producer.repository.ErrorLogRepository;
import ru.berdnikov.producer.service.ErrorLogService;

import java.time.LocalDateTime;

/**
 * @author danilaberdnikov on ErrorLogServiceImpl.
 * @project producer
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ErrorLogServiceImpl implements ErrorLogService {
    private final ErrorLogRepository errorLogRepository;

    @Override
    public void addErrorLog(String errorMessage) {
        ErrorLog errorLog = new ErrorLog();
        errorLog.setErrorMessage(errorMessage);
        errorLog.setLocalDateTime(LocalDateTime.now());
        errorLogRepository.save(errorLog);
    }

    @Override
    public Long count() {
        return errorLogRepository.count();
    }
}
