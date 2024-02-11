package kolesov.maksim.mapping.auth.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "app")
public class ServiceConfig {

    private Long accessTtl;

    private Long refreshTtl;

}
