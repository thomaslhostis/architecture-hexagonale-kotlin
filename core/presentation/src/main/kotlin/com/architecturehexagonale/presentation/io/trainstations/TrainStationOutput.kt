package com.architecturehexagonale.presentation.io.trainstations

import com.architecturehexagonale.domain.trainstations.entities.TrainStation

data class TrainStationOutput(
    val code: String,
    val label: String
) {
    constructor(trainStation: TrainStation) : this(
        trainStation.code,
        trainStation.label
    )
}
