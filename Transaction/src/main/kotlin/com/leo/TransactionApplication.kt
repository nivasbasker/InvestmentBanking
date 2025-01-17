package com.leo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.context.annotation.Bean
import org.springframework.web.reactive.function.client.WebClient

@SpringBootApplication
class TransactionApplication {
    @LoadBalanced
    @Bean
    fun webClientBuilder(): WebClient.Builder {
        return WebClient.builder()
    }
}

fun main(args: Array<String>) {
    runApplication<TransactionApplication>(*args)
}
