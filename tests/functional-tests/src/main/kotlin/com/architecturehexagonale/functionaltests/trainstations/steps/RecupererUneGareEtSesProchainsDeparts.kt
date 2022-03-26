package com.architecturehexagonale.functionaltests.trainstations.steps

import com.architecturehexagonale.functionaltests.commons.TestContext
import com.architecturehexagonale.functionaltests.trainstations.TrainStationsClient
import com.architecturehexagonale.functionaltests.trainstations.builders.TrainStationBuilder
import com.architecturehexagonale.presentation.io.trainstations.TrainStationWithNextDeparturesOutput
import io.cucumber.java.fr.Alors
import io.cucumber.java.fr.Lorsque
import io.cucumber.java.fr.Étantdonné
import io.cucumber.java8.Fr
import kotlin.test.assertEquals

class RecupererUneGareEtSesProchainsDeparts(
    private val trainStationBuilder: TrainStationBuilder,
    private val testContext: TestContext,
    private val trainStationsClient: TrainStationsClient
) : Fr {

    @Étantdonné("^le prochain départ pour \"([^\"]*)\" est à (\\d{1,2})h$")
    fun leProchainDepart(
        destination: String,
        departureHour: Int
    ) {
        trainStationBuilder.withNextDeparture(destination, departureHour)
    }

    @Lorsque("je récupère les informations et prochains départs de cette gare")
    fun jeRecupereLesInformationsEtProchainsDepartsDeCetteGare() {
        testContext.responseEntity = trainStationsClient.getTrainStationWithNextDepartures(trainStationBuilder.code)
    }

    @Alors("je reçois les informations et prochains départs de cette gare")
    fun jeRecoisLesInformationsEtProchainsDepartsDeCetteGare() {
        testContext.assertOk()
        val expectedTrainStationWithNextDeparturesOutput =
            trainStationBuilder.buildTrainStationWithNextDeparturesOutput()
        val actualTrainStationWithNextDeparturesOutput = testContext.getResponseBody(
            TrainStationWithNextDeparturesOutput::class
        )
        assertEquals(expectedTrainStationWithNextDeparturesOutput, actualTrainStationWithNextDeparturesOutput)
    }
}
