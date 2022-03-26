package com.architecturehexagonale.infrastructure.apis.idf

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate
import java.time.Duration

@Configuration
class IdfTrainStationsTemplateConfiguration(
    private val templateBuilder: RestTemplateBuilder,
    @Value("\${timeout.rest-template.connect}") private val connectTimeout: Long,
    @Value("\${timeout.rest-template.read}") private val readTimeout: Long
) {
    @Bean
    fun idfTrainStationsRestTemplate(): RestTemplate {
        return templateBuilder
            .setConnectTimeout(Duration.ofSeconds(connectTimeout))
            .setReadTimeout(Duration.ofSeconds(readTimeout))
            .build()
    }
}
