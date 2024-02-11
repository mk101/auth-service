package kolesov.maksim.mapping.auth.config;

import kolesov.maksim.mapping.auth.model.RefreshTokenEntity;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.UUID;

@Data
@Configuration
@ConfigurationProperties(prefix = "redis")
public class RedisConfig {

    private String host;
    private Integer port;
    private String password;

    @Bean
    RedisStandaloneConfiguration redisStandaloneConfiguration() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(host, port);
        configuration.setPassword(password);

        return configuration;
    }

    @Bean
    LettuceConnectionFactory connectionFactory() {
        return new LettuceConnectionFactory(redisStandaloneConfiguration());
    }

    @Bean
    RedisTemplate<RefreshTokenEntity, UUID> redisTemplate() {
        RedisTemplate<RefreshTokenEntity, UUID> template = new RedisTemplate<>();
        template.setEnableTransactionSupport(false);
        template.setConnectionFactory(connectionFactory());

        return template;
    }

}
