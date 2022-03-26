package com.architecturehexagonale.presentation.io.trainstations

import com.architecturehexagonale.domain.trainstations.views.TrainStationWithNextDepartures
import java.time.LocalDateTime

data class TrainStationWithNextDeparturesOutput(
    val code: String,
    val label: String,
    val nextDepartures: List<NextDeparture>
) {
    constructor(trainStationWithNextDepartures: TrainStationWithNextDepartures) : this(
        trainStationWithNextDepartures.code,
        trainStationWithNextDepartures.label,
        trainStationWithNextDepartures.nextDepartures.map(::NextDeparture)
    )

    data class NextDeparture(
        val destination: String?,
        val departureTime: LocalDateTime?
    ) {
        constructor(nextDeparture: TrainStationWithNextDepartures.NextDeparture) : this(
            nextDeparture.destination,
            nextDeparture.departureTime
        )
    }
}
