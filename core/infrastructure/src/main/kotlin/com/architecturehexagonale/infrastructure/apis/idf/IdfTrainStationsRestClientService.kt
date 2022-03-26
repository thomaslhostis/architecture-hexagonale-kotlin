package com.architecturehexagonale.infrastructure.apis.idf

import com.architecturehexagonale.domain.trainstations.services.TrainStationsService
import com.architecturehexagonale.domain.trainstations.views.TrainStationWithNextDepartures
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class IdfTrainStationsRestClientService(
    private val idfTrainStationsRestTemplate: RestTemplate
) : TrainStationsService {

    override fun findTrainStationNextDepartures(
        trainStationCode: String
    ): List<TrainStationWithNextDepartures.NextDeparture> {

        val idfNextDeparturesResponse = idfTrainStationsRestTemplate.getForEntity(
            "https://api.idf.fr/gares?code=$trainStationCode",
            IdfTrainStationNextDeparturesResponse::class.java
        )

        return idfNextDeparturesResponse
            .body
            ?.toDomainNextDepartures()
            .orEmpty()
    }
}
