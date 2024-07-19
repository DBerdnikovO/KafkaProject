package ru.berdnikov.producer.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

/**
 * @author danilaberdnikov on Config.
 * @project producer
 */
@OpenAPIDefinition(
        info = @Info(
                title = "Metric producer Api",
                description = "Metric producer project",
                version = "0.0.1",
                contact = @Contact(
                        name = "Berdnikov Danila"
                )
        )
)
public class OpenApiConfig {

}
