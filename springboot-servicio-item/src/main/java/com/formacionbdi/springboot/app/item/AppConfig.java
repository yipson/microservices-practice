package com.formacionbdi.springboot.app.item;


import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class AppConfig {

    @Bean("clienteRest")
    @LoadBalanced
    public RestTemplate registrarRestTemplate(){
        return new RestTemplate();
    }

    @Bean
    public Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer() {
        return factory -> factory.configureDefault(id -> {
            return new Resilience4JConfigBuilder(id)
                    .circuitBreakerConfig(CircuitBreakerConfig.custom()
                            //tamaño de ventana que mide solicitudes fallidas y exitosas
                            .slidingWindowSize(10)
                            // porcentaje de solicitudes fallidas para entrar en estado cerrado
                            .failureRateThreshold(50)
                            // tiempo de espera para salir del estado cerrado
                            .waitDurationInOpenState(Duration.ofSeconds(10L))
                            // numero de solicitudes de prueba para salir del estado semi abierto
                            .permittedNumberOfCallsInHalfOpenState(5)
                            // porcentaje de umbral de llamadas lentas
                            .slowCallRateThreshold(50)
                            // tiempo maximo de debiese demorar cada llamada particular
                            .slowCallDurationThreshold(Duration.ofSeconds(2L))
                            .build())
                    .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(6L)).build())
                    .build();
        });
    }
}
