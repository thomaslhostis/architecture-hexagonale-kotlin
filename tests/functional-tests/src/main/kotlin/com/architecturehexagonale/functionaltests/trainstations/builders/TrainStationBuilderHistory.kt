package com.architecturehexagonale.functionaltests.trainstations.builders

import com.architecturehexagonale.presentation.io.trainstations.TrainStationOutput
import org.springframework.stereotype.Component

@Component
class TrainStationBuilderHistory(var trainStationBuilders: MutableList<TrainStationBuilder>) {

    fun reset() {
        trainStationBuilders = mutableListOf()
    }

    fun addTrainStationBuilder(trainStationBuilder: TrainStationBuilder) {
        trainStationBuilders.add(trainStationBuilder.copy())
    }

    fun buildTrainStationOutputs(): List<TrainStationOutput> {
        return trainStationBuilders.map(TrainStationBuilder::buildTrainStationOutput)
    }
}
