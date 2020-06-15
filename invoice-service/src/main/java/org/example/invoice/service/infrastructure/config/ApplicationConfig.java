package org.example.invoice.service.infrastructure.config;

import io.zeebe.spring.client.EnableZeebeClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableZeebeClient
@EnableJpaAuditing
@EnableTransactionManagement
@EnableJpaRepositories("org.example.invoice.service.infrastructure.persistence")
public class ApplicationConfig {
}
