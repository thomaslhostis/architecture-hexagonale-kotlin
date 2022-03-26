package com.architecturehexagonale.functionaltests.configuration

import com.architecturehexagonale.functionaltests.trainstations.builders.TrainStationBuilder
import com.architecturehexagonale.functionaltests.trainstations.builders.TrainStationBuilderHistory
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class TestContextCleaner(
    private val testRestTemplate: RestTemplate,
    private val trainStationBuilder: TrainStationBuilder,
    private val trainStationBuilderHistory: TrainStationBuilderHistory
) {

    fun reset() {
        testRestTemplate.interceptors = emptyList()
        trainStationBuilder.reset()
        trainStationBuilderHistory.reset()
    }
}
