package com.architecturehexagonale.domain.trainstations.services

import com.architecturehexagonale.domain.trainstations.views.TrainStationWithNextDepartures

interface TrainStationsService {
    fun findTrainStationNextDepartures(trainStationCode: String): List<TrainStationWithNextDepartures.NextDeparture>
}
