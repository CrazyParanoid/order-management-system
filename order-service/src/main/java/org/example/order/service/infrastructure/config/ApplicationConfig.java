package org.example.order.service.infrastructure.config;

import io.zeebe.spring.client.EnableZeebeClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableZeebeClient
@EnableJpaAuditing
@EnableTransactionManagement
@EnableJpaRepositories("org.example.order.service.infrastructure.persistence")
public class ApplicationConfig implements WebMvcConfigurer {
}
