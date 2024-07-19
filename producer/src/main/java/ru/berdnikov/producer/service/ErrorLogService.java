package ru.berdnikov.producer.service;

/**
 * @author danilaberdnikov on ErrorLogService.
 * @project producer
 */
public interface ErrorLogService {
    void addErrorLog(String errorMessage);

    Long count();
}
