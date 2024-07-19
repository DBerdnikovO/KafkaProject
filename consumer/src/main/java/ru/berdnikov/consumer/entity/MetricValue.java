package ru.berdnikov.consumer.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author danilaberdnikov on MetricValue.
 * @project consumer
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "metric_value")
public class MetricValue {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "metric_name_id", nullable = false)
    private Metric metric;

    @Column(name = "value")
    private Double value;

    @Column(name = "date_time")
    private LocalDateTime localDateTime;
}
