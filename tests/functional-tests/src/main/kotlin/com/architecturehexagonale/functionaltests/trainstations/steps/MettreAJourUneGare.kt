package com.architecturehexagonale.functionaltests.trainstations.steps

import com.architecturehexagonale.functionaltests.commons.TestContext
import com.architecturehexagonale.functionaltests.trainstations.TrainStationsClient
import com.architecturehexagonale.functionaltests.trainstations.builders.TrainStationBuilder
import io.cucumber.java.fr.Alors
import io.cucumber.java.fr.Lorsque
import io.cucumber.java8.Fr

class MettreAJourUneGare(
    private val trainStationBuilder: TrainStationBuilder,
    private val testContext: TestContext,
    private val trainStationsClient: TrainStationsClient
) : Fr {

    @Lorsque("^je mets à jour cette gare avec le libellé \"([^\"]*)\"$")
    fun jeMetsAJourCetteGare(trainStationLabel: String) {
        trainStationBuilder.withLabel(trainStationLabel)
        val trainStationInput = trainStationBuilder.buildTrainStationInput()
        testContext.responseEntity = trainStationsClient.updateTrainStation(trainStationInput)
    }

    @Lorsque("^je tente de mettre à jour la gare \"([^\"]*)\"$")
    fun jeTenteDeMettreAJourLaGare(trainStationCode: String) {
        trainStationBuilder.withCode(trainStationCode)
        val trainStationInput = trainStationBuilder.buildTrainStationInput()
        testContext.responseEntity = trainStationsClient.updateTrainStation(trainStationInput, "should fail")
    }

    @Alors("cette gare est mise à jour avec succès")
    fun cetteGareEstMiseAJourAvecSucces() {
        testContext.assertOk()
    }
}
