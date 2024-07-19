package ru.berdnikov.consumer.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

/**
 * @author danilaberdnikov on OpenApiConfig.
 * @project consumer
 */
@OpenAPIDefinition(
        info = @Info(
                title = "Metric consumer Api",
                description = "Metric consumer project",
                version = "0.0.1",
                contact = @Contact(
                        name = "Berdnikov Danila"
                )
        )
)
public class OpenApiConfig {

}
