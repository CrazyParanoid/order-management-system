package org.example.payment.service.infrastructure.config;

import io.zeebe.spring.client.EnableZeebeClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableZeebeClient
@EnableJpaAuditing
@EnableTransactionManagement
@EnableJpaRepositories("org.example.payment.service.infrastructure.persistence")
public class ApplicationConfig {
}
