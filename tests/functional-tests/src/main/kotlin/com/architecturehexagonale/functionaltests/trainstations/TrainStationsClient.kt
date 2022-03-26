package com.architecturehexagonale.functionaltests.trainstations

import com.architecturehexagonale.functionaltests.commons.TestContext.Companion.getResponseType
import com.architecturehexagonale.functionaltests.trainstations.builders.TrainStationBuilder
import com.architecturehexagonale.presentation.io.trainstations.TrainStationInput
import com.architecturehexagonale.presentation.io.trainstations.TrainStationOutput
import com.architecturehexagonale.presentation.io.trainstations.TrainStationWithNextDeparturesOutput
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod.DELETE
import org.springframework.http.HttpMethod.PUT
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.test.web.client.MockRestServiceServer.createServer
import org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo
import org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess
import org.springframework.web.client.RestTemplate

@Component
class TrainStationsClient(
    private val testRestTemplate: RestTemplate,
    private val idfTrainStationsRestTemplate: RestTemplate,
    private val trainStationBuilder: TrainStationBuilder
) {
    fun createTrainStation(
        trainStationInput: TrainStationInput,
        tryTo: String?
    ): ResponseEntity<*> {
        val responseType = getResponseType(tryTo, HttpStatus::class)
        return testRestTemplate.postForEntity(
            "/api/train-station",
            trainStationInput,
            responseType
        )
    }

    fun getTrainStationWithNextDepartures(trainStationCode: String?): ResponseEntity<TrainStationWithNextDeparturesOutput> {

        val idfMockServer = createServer(idfTrainStationsRestTemplate)
        val jsonResponse = trainStationBuilder.buildIdfTrainStationNextDeparturesJsonResponse()
        val requestMatcher = requestTo("https://api.idf.fr/gares?code=$trainStationCode")
        idfMockServer.expect(requestMatcher).andRespond(withSuccess(jsonResponse, APPLICATION_JSON))

        return testRestTemplate.getForEntity(
            "/api/train-station-with-next-departures?train_station_code=$trainStationCode",
            TrainStationWithNextDeparturesOutput::class.java
        )
    }

    fun updateTrainStation(
        trainStationInput: TrainStationInput,
        tryTo: String? = null
    ): ResponseEntity<*> {
        val responseType = getResponseType(tryTo, HttpStatus::class)
        return testRestTemplate.exchange(
            "/api/train-station",
            PUT,
            HttpEntity(trainStationInput),
            responseType
        )
    }

    fun getAllTrainStations(): ResponseEntity<Array<TrainStationOutput>> {
        return testRestTemplate.getForEntity(
            "/api/train-stations",
            Array<TrainStationOutput>::class.java
        )
    }

    fun deleteTrainStation(
        trainStationCode: String?,
        tryTo: String? = null
    ): ResponseEntity<*> {
        val responseType = getResponseType(tryTo, HttpStatus::class)
        return testRestTemplate.exchange(
            "/api/train-station?train_station_code=$trainStationCode",
            DELETE,
            null,
            responseType
        )
    }
}
