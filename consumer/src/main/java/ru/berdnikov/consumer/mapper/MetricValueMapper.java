package ru.berdnikov.consumer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.berdnikov.consumer.dto.MetricValueDTO;
import ru.berdnikov.consumer.entity.MetricValue;

/**
 * @author danilaberdnikov on MetricValueMapper.
 * @project consumer
 */
@Mapper(componentModel = "spring")
public interface MetricValueMapper {
    MetricValueDTO toDto(MetricValue metricValue);

    @Mapping(target = "id", ignore = true)
    MetricValue toEntity(MetricValueDTO metricValueDTO);
}
