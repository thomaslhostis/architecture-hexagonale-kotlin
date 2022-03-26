package com.architecturehexagonale.functionaltests.trainstations.steps

import com.architecturehexagonale.functionaltests.commons.TestContext
import com.architecturehexagonale.functionaltests.trainstations.TrainStationsClient
import com.architecturehexagonale.functionaltests.trainstations.builders.TrainStationBuilderHistory
import com.architecturehexagonale.presentation.io.trainstations.TrainStationOutput
import io.cucumber.java.fr.Alors
import io.cucumber.java.fr.Lorsque
import io.cucumber.java8.Fr
import kotlin.test.assertEquals

class ListerLesGares(
    private val trainStationBuilderHistory: TrainStationBuilderHistory,
    private val testContext: TestContext,
    private val trainStationsClient: TrainStationsClient
) : Fr {

    @Lorsque("je récupère la liste des gares")
    fun jeRecupereLaListeDesGares() {
        testContext.responseEntity = trainStationsClient.getAllTrainStations()
    }

    @Alors("je reçois toutes les gares")
    fun jeRecoisToutesLesGares() {
        testContext.assertOk()
        val expectedTrainStationOutputs = trainStationBuilderHistory.buildTrainStationOutputs()
        val actualTrainStationOutputs = testContext.getResponseBody(Array<TrainStationOutput>::class).toList()
        assertEquals(expectedTrainStationOutputs, actualTrainStationOutputs)
    }
}
