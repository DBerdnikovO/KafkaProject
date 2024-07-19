package ru.berdnikov.consumer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.berdnikov.consumer.dto.MetricDTO;
import ru.berdnikov.consumer.entity.Metric;

/**
 * @author danilaberdnikov on MetricMapper.
 * @project consumer
 */
@Mapper(componentModel = "spring", uses = {MetricValueMapper.class})
public interface MetricMapper {
    @Mapping(source = "name", target = "metricName")
    @Mapping(source = "metricValues", target = "valueDTO")
    MetricDTO toDto(Metric metric);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "valueDTO", target = "metricValues")
    Metric toEntity(MetricDTO metricDto);
}
