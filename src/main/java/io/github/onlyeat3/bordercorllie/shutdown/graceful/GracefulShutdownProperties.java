package io.github.onlyeat3.bordercorllie.shutdown.graceful;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@Data
@EnableConfigurationProperties(GracefulShutdownProperties.class)
@ConfigurationProperties(prefix = "graceful.shutdown")
public class GracefulShutdownProperties {
    private Integer tomcatTimeout = 30;
}
