package io.github.onlyeat3.bordercorllie.shutdown.graceful;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnWebApplication
@EnableConfigurationProperties(GracefulShutdownProperties.class)
public class GracefulShutdownAutoConfiguration {
    @Autowired private GracefulShutdownContextClosedEventListener gracefulShutdownContextClosedEventListener;
    @Autowired private TomcatServletWebServerFactory tomcatServletWebServerFactory;

    @Bean
    public GracefulShutdownContextClosedEventListener getGracefulShutdownContextClosedEventListener(@Autowired GracefulShutdownProperties gracefulShutdownProperties){
        GracefulShutdownContextClosedEventListener gracefulShutdownContextClosedEventListener = new GracefulShutdownContextClosedEventListener();
        gracefulShutdownContextClosedEventListener.setGracefulShutdownProperties(gracefulShutdownProperties);
        return gracefulShutdownContextClosedEventListener;
    }

    @Bean
    @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> gracefulShutdownWebServerFactoryustomizer() {
        return (tomcatFactory) -> tomcatFactory.addConnectorCustomizers(gracefulShutdownContextClosedEventListener);
    }
}
