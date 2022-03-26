package com.architecturehexagonale.application.trainstations

import com.architecturehexagonale.domain.trainstations.entities.TrainStation
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

    fun createTrainStation(trainStation: TrainStation) {
        trainStation.validate()
        if (trainStationsProjectionRepository.existsByCode(trainStation.code)) {
            throw IllegalArgumentException("La station de train ${trainStation.code} existe déjà")
        }
        trainStationsProjectionRepository.createTrainStation(trainStation)
    }

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

    fun updateTrainStation(trainStation: TrainStation) {
        trainStation.validate()
        if (!trainStationsProjectionRepository.existsByCode(trainStation.code)) {
            throw TrainStationNotFoundException(trainStation.code)
        }
        trainStationsProjectionRepository.updateTrainStation(trainStation)
    }

    fun deleteTrainStation(trainStationCode: String) {
        if (!trainStationsProjectionRepository.existsByCode(trainStationCode)) {
            throw TrainStationNotFoundException(trainStationCode)
        }
        trainStationsProjectionRepository.deleteTrainStation(trainStationCode)
    }
}
