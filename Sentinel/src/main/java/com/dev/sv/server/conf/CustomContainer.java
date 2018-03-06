package com.dev.sv.server.conf;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

/**
 * Created by Sahan on 25/6/2017.
 */
@Configuration
//@EnableMongoRepositories(basePackages = "com.dev.db")
@EnableScheduling
@Component
//@EnableAutoConfiguration
public class CustomContainer implements EmbeddedServletContainerCustomizer {

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {

        container.setPort(8381);
    }
}
