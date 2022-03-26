package com.architecturehexagonale.domain.trainstations.projectionrepositories

import com.architecturehexagonale.domain.trainstations.entities.TrainStation

interface TrainStationsProjectionRepository {
    fun existsByCode(trainStationCode: String): Boolean
    fun createTrainStation(trainStation: TrainStation)
    fun findByCode(trainStationCode: String): TrainStation?
    fun updateTrainStation(trainStation: TrainStation)
    fun findAllTrainStations(): List<TrainStation>
    fun deleteTrainStation(trainStationCode: String)
}
