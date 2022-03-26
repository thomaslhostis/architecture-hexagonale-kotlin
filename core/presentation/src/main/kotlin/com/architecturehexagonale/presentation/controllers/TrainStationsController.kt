package com.architecturehexagonale.presentation.controllers

import com.architecturehexagonale.application.trainstations.TrainStationsUseCases
import com.architecturehexagonale.domain.trainstations.projectionrepositories.TrainStationsProjectionRepository
import com.architecturehexagonale.presentation.io.trainstations.TrainStationInput
import com.architecturehexagonale.presentation.io.trainstations.TrainStationOutput
import com.architecturehexagonale.presentation.io.trainstations.TrainStationWithNextDeparturesOutput
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class TrainStationsController(
    private val trainStationsUseCases: TrainStationsUseCases,
    private val trainStationsProjectionRepository: TrainStationsProjectionRepository
) {

    @PostMapping("/api/train-station")
    fun createTrainStation(
        @RequestBody trainStationInput: TrainStationInput
    ): ResponseEntity<HttpStatus> {
        TODO("Not yet implemented")
        // 1. Transformer l'input en objet du domaine
        // 2. Utiliser `trainStationsUseCases` pour gérer la création
        // 3. Retourner ResponseEntity(OK)
    }

    @GetMapping("/api/train-station-with-next-departures")
    fun getStationWithNextDepartures(
        @RequestParam("train_station_code") trainStationCode: String
    ): ResponseEntity<TrainStationWithNextDeparturesOutput> {
        val trainStationWithNextDepartures =
            trainStationsUseCases.findTrainStationWithNextDepartures(trainStationCode)
        val trainStationWithNextDeparturesOutput = TrainStationWithNextDeparturesOutput(trainStationWithNextDepartures)
        return ResponseEntity.ok(trainStationWithNextDeparturesOutput)
    }

    @PutMapping("/api/train-station")
    fun updateTrainStation(
        @RequestBody trainStationInput: TrainStationInput
    ): ResponseEntity<HttpStatus> {
        TODO("Not yet implemented")
        // 1. Transformer l'input en objet du domaine
        // 2. Utiliser `trainStationsUseCases` pour mettre à jour
        // 3. Retourner ResponseEntity(OK)
    }

    @GetMapping("/api/train-stations")
    fun getAllTrainStations(): ResponseEntity<List<TrainStationOutput>> {
        TODO("Not yet implemented")
        // 1. Utiliser `trainStationsProjectionRepository` directement pour récupérer les gares
        // 2. Convertir au format de sortie
        // 3. Retourner le résultat
    }

    //TODO Bonus : suppression d'une gare
}
