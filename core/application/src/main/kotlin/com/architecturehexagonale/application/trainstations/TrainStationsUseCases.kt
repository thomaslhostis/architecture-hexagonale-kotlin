package com.architecturehexagonale.application.trainstations

import com.architecturehexagonale.domain.trainstations.entities.TrainStation
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
        TODO("Not yet implemented")
        // 1. Récupérer la gare stockée en base de données à partir du code
        // 2. Vérifier qu'elle existe, sinon `TrainStationNotFoundException`
        // 3. Appeler le service partenaire pour récupérer les prochains départs de cette gare
        // 4. Agréger et retourner le tout
    }

    fun updateTrainStation(trainStation: TrainStation) {
        TODO("Not yet implemented")
        // 1. Vérifier que le format du code de gare commence par "TRN_"
        // 2. Vérifier que la gare existe à partir du code
        // 3. Utiliser `trainStationsProjectionRepository` pour mettre à jour
    }
}
