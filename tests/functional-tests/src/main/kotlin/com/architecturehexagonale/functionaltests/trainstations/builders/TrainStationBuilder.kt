package com.architecturehexagonale.functionaltests.trainstations.builders

import com.architecturehexagonale.functionaltests.commons.TestJackson
import com.architecturehexagonale.infrastructure.apis.idf.IdfTrainStationNextDeparturesResponse
import com.architecturehexagonale.presentation.io.trainstations.TrainStationInput
import com.architecturehexagonale.presentation.io.trainstations.TrainStationOutput
import com.architecturehexagonale.presentation.io.trainstations.TrainStationWithNextDeparturesOutput
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.LocalDateTime.now

@Component
data class TrainStationBuilder(
    var code: String?,
    var label: String?,
    var nextDepartures: MutableList<NextDeparture>
) {

    data class NextDeparture(
        val destination: String,
        val departureTime: LocalDateTime
    )

    fun reset() = apply {
        code = "TRN_ABC"
        label = "Station Abc"
        nextDepartures = mutableListOf()
    }

    fun withCode(code: String) = apply { this.code = code }
    fun withLabel(label: String) = apply { this.label = label }

    fun withNextDeparture(destination: String, departureHour: Int) {
        val departureTime = now()
            .withHour(departureHour)
            .withMinute(0)
            .withSecond(0)
            .withNano(0)
        nextDepartures.add(
            NextDeparture(
                destination,
                departureTime
            )
        )
    }

    fun buildTrainStationInput() = TrainStationInput(
        code.orEmpty(),
        label.orEmpty()
    )

    fun buildTrainStationWithNextDeparturesOutput() = TrainStationWithNextDeparturesOutput(
        code.orEmpty(),
        label.orEmpty(),
        nextDepartures.map { nextDeparture ->
            TrainStationWithNextDeparturesOutput.NextDeparture(
                nextDeparture.destination,
                nextDeparture.departureTime
            )
        }
    )

    fun buildTrainStationOutput() = TrainStationOutput(
        code.orEmpty(),
        label.orEmpty()
    )

    fun buildIdfTrainStationNextDeparturesJsonResponse(): String {
        val idfTrainStationNextDeparturesResponse = IdfTrainStationNextDeparturesResponse(
            nextDepartures.map { nextDeparture ->
                IdfTrainStationNextDeparturesResponse.NextDeparture(
                    nextDeparture.destination,
                    nextDeparture.departureTime
                )
            }
        )
        return TestJackson.write(idfTrainStationNextDeparturesResponse)
    }
}
