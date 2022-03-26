package com.architecturehexagonale.infrastructure.mongo.trainstations

import com.architecturehexagonale.domain.trainstations.entities.TrainStation
import com.architecturehexagonale.domain.trainstations.exceptions.TrainStationNotFoundException
import com.architecturehexagonale.domain.trainstations.projectionrepositories.TrainStationsProjectionRepository
import org.springframework.stereotype.Repository

@Repository
class MongoTrainStationsProjectionRepository(
    private val trainStationsRepository: MongoTrainStationsRepository
) : TrainStationsProjectionRepository {

    override fun existsByCode(trainStationCode: String) = trainStationsRepository.existsByCode(trainStationCode)

    override fun createTrainStation(trainStation: TrainStation) {
        val trainStationDocument = TrainStationDocument(trainStation)
        trainStationsRepository.save(trainStationDocument)
    }

    override fun findByCode(trainStationCode: String): TrainStation? {
        return trainStationsRepository
            .findByCode(trainStationCode)
            ?.toDomainInstance()
    }

    override fun updateTrainStation(trainStation: TrainStation) {
        val existingTrainStationDocument = trainStationsRepository.findByCode(trainStation.code)
            ?: throw TrainStationNotFoundException(trainStation.code)
        existingTrainStationDocument.label = trainStation.label
        trainStationsRepository.save(existingTrainStationDocument)
    }

    override fun findAllTrainStations(): List<TrainStation> {
        return trainStationsRepository.findAll().map(TrainStationDocument::toDomainInstance)
    }

    override fun deleteTrainStation(trainStationCode: String) {
        trainStationsRepository.deleteByCode(trainStationCode)
    }
}
