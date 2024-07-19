package ru.berdnikov.producer.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author danilaberdnikov on ErrorLog.
 * @project producer
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "error_log")
public class ErrorLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "error_message")
    private String errorMessage;

    @Column(name = "date_time")
    private LocalDateTime localDateTime;
}
