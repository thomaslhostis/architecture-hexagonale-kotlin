package com.architecturehexagonale.domain.trainstations.views

import java.time.LocalDateTime

class TrainStationWithNextDepartures(
    val code: String,
    val label: String,
    val nextDepartures: List<NextDeparture>
) {
    class NextDeparture(
        val destination: String?,
        val departureTime: LocalDateTime?
    )
}
