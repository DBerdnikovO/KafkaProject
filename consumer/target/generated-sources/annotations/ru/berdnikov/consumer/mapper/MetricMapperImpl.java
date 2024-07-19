package ru.berdnikov.consumer.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.berdnikov.consumer.dto.MetricDTO;
import ru.berdnikov.consumer.dto.MetricValueDTO;
import ru.berdnikov.consumer.entity.Metric;
import ru.berdnikov.consumer.entity.MetricValue;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-19T12:10:51+0400",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class MetricMapperImpl implements MetricMapper {

    @Autowired
    private MetricValueMapper metricValueMapper;

    @Override
    public MetricDTO toDto(Metric metric) {
        if ( metric == null ) {
            return null;
        }

        MetricDTO metricDTO = new MetricDTO();

        metricDTO.setMetricName( metric.getName() );
        metricDTO.setValueDTO( metricValueListToMetricValueDTOList( metric.getMetricValues() ) );

        return metricDTO;
    }

    @Override
    public Metric toEntity(MetricDTO metricDto) {
        if ( metricDto == null ) {
            return null;
        }

        Metric metric = new Metric();

        metric.setMetricValues( metricValueDTOListToMetricValueList( metricDto.getValueDTO() ) );

        return metric;
    }

    protected List<MetricValueDTO> metricValueListToMetricValueDTOList(List<MetricValue> list) {
        if ( list == null ) {
            return null;
        }

        List<MetricValueDTO> list1 = new ArrayList<MetricValueDTO>( list.size() );
        for ( MetricValue metricValue : list ) {
            list1.add( metricValueMapper.toDto( metricValue ) );
        }

        return list1;
    }

    protected List<MetricValue> metricValueDTOListToMetricValueList(List<MetricValueDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<MetricValue> list1 = new ArrayList<MetricValue>( list.size() );
        for ( MetricValueDTO metricValueDTO : list ) {
            list1.add( metricValueMapper.toEntity( metricValueDTO ) );
        }

        return list1;
    }
}
