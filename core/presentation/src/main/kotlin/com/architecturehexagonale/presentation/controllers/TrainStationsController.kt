package com.architecturehexagonale.presentation.controllers

import com.architecturehexagonale.application.trainstations.TrainStationsUseCases
import com.architecturehexagonale.presentation.io.trainstations.TrainStationWithNextDeparturesOutput
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class TrainStationsController(
    private val trainStationsUseCases: TrainStationsUseCases
) {

    @GetMapping("/api/train-station-with-next-departures")
    fun getStationWithNextDepartures(
        @RequestParam("train_station_code") trainStationCode: String
    ): ResponseEntity<TrainStationWithNextDeparturesOutput> {
        val trainStationWithNextDepartures =
            trainStationsUseCases.findTrainStationWithNextDepartures(trainStationCode)
        val trainStationWithNextDeparturesOutput = TrainStationWithNextDeparturesOutput(trainStationWithNextDepartures)
        return ResponseEntity.ok(trainStationWithNextDeparturesOutput)
    }
}
