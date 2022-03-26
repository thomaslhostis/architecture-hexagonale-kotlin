package com.architecturehexagonale.presentation.controllers

import com.architecturehexagonale.application.trainstations.TrainStationsUseCases
import com.architecturehexagonale.domain.trainstations.projectionrepositories.TrainStationsProjectionRepository
import com.architecturehexagonale.presentation.io.trainstations.TrainStationInput
import com.architecturehexagonale.presentation.io.trainstations.TrainStationOutput
import com.architecturehexagonale.presentation.io.trainstations.TrainStationWithNextDeparturesOutput
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
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
        val trainStation = trainStationInput.toDomainInstance()
        trainStationsUseCases.createTrainStation(trainStation)
        return ResponseEntity(CREATED)
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
        val trainStation = trainStationInput.toDomainInstance()
        trainStationsUseCases.updateTrainStation(trainStation)
        return ResponseEntity(OK)
    }

    @GetMapping("/api/train-stations")
    fun getAllTrainStations(): ResponseEntity<List<TrainStationOutput>> {
        val allTrainStations = trainStationsProjectionRepository.findAllTrainStations()
        val allTrainStationOutputs = allTrainStations.map(::TrainStationOutput)
        return ResponseEntity.ok(allTrainStationOutputs)
    }

    @DeleteMapping("/api/train-station")
    fun deleteTrainStation(
        @RequestParam("train_station_code") trainStationCode: String
    ): ResponseEntity<HttpStatus> {
        trainStationsUseCases.deleteTrainStation(trainStationCode)
        return ResponseEntity(OK)
    }
}
