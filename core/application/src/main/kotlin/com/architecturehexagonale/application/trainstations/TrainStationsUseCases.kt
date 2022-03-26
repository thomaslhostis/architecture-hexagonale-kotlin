package com.architecturehexagonale.application.trainstations

import com.architecturehexagonale.domain.trainstations.exceptions.TrainStationNotFoundException
import com.architecturehexagonale.domain.trainstations.projectionrepositories.TrainStationsProjectionRepository
import com.architecturehexagonale.domain.trainstations.services.TrainStationsService
import com.architecturehexagonale.domain.trainstations.views.TrainStationWithNextDepartures
import org.springframework.stereotype.Component

@Component
class TrainStationsUseCases(
    private val trainStationsProjectionRepository: TrainStationsProjectionRepository,
    private val trainStationsService: TrainStationsService
) {

    fun findTrainStationWithNextDepartures(trainStationCode: String): TrainStationWithNextDepartures {
        val trainStation = trainStationsProjectionRepository.findByCode(trainStationCode)
            ?: throw TrainStationNotFoundException(trainStationCode)
        val nextDepartures = trainStationsService.findTrainStationNextDepartures(trainStationCode)
        return TrainStationWithNextDepartures(
            trainStation.code,
            trainStation.label,
            nextDepartures
        )
    }
}
