package com.architecturehexagonale.domain.trainstations.projectionrepositories

import com.architecturehexagonale.domain.trainstations.entities.TrainStation

interface TrainStationsProjectionRepository {
    fun findByCode(trainStationCode: String): TrainStation?
}
