package com.architecturehexagonale.domain.trainstations.exceptions

import com.architecturehexagonale.domain.exceptions.NotFoundException

class TrainStationNotFoundException(trainStationCode: String) :
    NotFoundException("La station de train $trainStationCode n'existe pas")
