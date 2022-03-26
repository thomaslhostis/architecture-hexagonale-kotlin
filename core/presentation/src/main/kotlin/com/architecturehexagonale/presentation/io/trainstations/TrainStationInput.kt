package com.architecturehexagonale.presentation.io.trainstations

import com.architecturehexagonale.domain.trainstations.entities.TrainStation
import javax.validation.constraints.NotEmpty

data class TrainStationInput(
    @NotEmpty
    val code: String,
    @NotEmpty
    val label: String
) {
    fun toDomainInstance() = TrainStation(
        code,
        label
    )
}
