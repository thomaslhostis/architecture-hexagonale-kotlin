package com.architecturehexagonale.infrastructure.apis.idf

import com.architecturehexagonale.domain.trainstations.views.TrainStationWithNextDepartures
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

class IdfTrainStationNextDeparturesResponse(
    @field:JsonProperty("next_departures")
    val nextDepartures: List<NextDeparture>?
) {
    fun toDomainNextDepartures(): List<TrainStationWithNextDepartures.NextDeparture> {
        return nextDepartures
            ?.map { nextDeparture ->
                TrainStationWithNextDepartures.NextDeparture(
                    nextDeparture.destination,
                    nextDeparture.departureTime
                )
            }.orEmpty()
    }

    class NextDeparture(
        val destination: String?,
        @field:JsonProperty("departure_time")
        @field:JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        val departureTime: LocalDateTime?
    )
}
