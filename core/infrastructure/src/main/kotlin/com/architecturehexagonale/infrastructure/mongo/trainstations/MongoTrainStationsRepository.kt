package com.architecturehexagonale.infrastructure.mongo.trainstations

import org.springframework.data.mongodb.repository.MongoRepository

interface MongoTrainStationsRepository : MongoRepository<TrainStationDocument, String> {
    fun existsByCode(trainStationCode: String): Boolean
    fun findByCode(trainStationCode: String): TrainStationDocument?
    fun deleteByCode(trainStationCode: String)
}
