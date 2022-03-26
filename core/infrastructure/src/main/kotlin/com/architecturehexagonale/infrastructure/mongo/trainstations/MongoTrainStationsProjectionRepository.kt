package com.architecturehexagonale.infrastructure.mongo.trainstations

import com.architecturehexagonale.domain.trainstations.entities.TrainStation
import com.architecturehexagonale.domain.trainstations.projectionrepositories.TrainStationsProjectionRepository
import org.springframework.stereotype.Repository

@Repository
class MongoTrainStationsProjectionRepository(
    private val trainStationsRepository: MongoTrainStationsRepository
) : TrainStationsProjectionRepository {

    override fun findByCode(trainStationCode: String): TrainStation? {
        return trainStationsRepository
            .findByCode(trainStationCode)
            ?.toDomainInstance()
    }
}
