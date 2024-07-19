package ru.berdnikov.consumer.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.berdnikov.consumer.dto.MetricValueDTO;
import ru.berdnikov.consumer.entity.MetricValue;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-19T12:10:51+0400",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class MetricValueMapperImpl implements MetricValueMapper {

    @Override
    public MetricValueDTO toDto(MetricValue metricValue) {
        if ( metricValue == null ) {
            return null;
        }

        MetricValueDTO metricValueDTO = new MetricValueDTO();

        metricValueDTO.setValue( metricValue.getValue() );
        metricValueDTO.setLocalDateTime( metricValue.getLocalDateTime() );

        return metricValueDTO;
    }

    @Override
    public MetricValue toEntity(MetricValueDTO metricValueDTO) {
        if ( metricValueDTO == null ) {
            return null;
        }

        MetricValue metricValue = new MetricValue();

        metricValue.setValue( metricValueDTO.getValue() );
        metricValue.setLocalDateTime( metricValueDTO.getLocalDateTime() );

        return metricValue;
    }
}
