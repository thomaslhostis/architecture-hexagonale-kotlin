package com.architecturehexagonale.functionaltests.trainstations.steps

import com.architecturehexagonale.functionaltests.commons.TestContext
import com.architecturehexagonale.functionaltests.trainstations.TrainStationsClient
import com.architecturehexagonale.functionaltests.trainstations.builders.TrainStationBuilder
import com.architecturehexagonale.functionaltests.trainstations.builders.TrainStationBuilderHistory
import io.cucumber.java.fr.Alors
import io.cucumber.java.fr.Lorsque
import io.cucumber.java.fr.Étantdonnée
import io.cucumber.java8.Fr

class CreerUneGare(
    private val trainStationBuilder: TrainStationBuilder,
    private val trainStationBuilderHistory: TrainStationBuilderHistory,
    private val testContext: TestContext,
    private val trainStationsClient: TrainStationsClient
) : Fr {

    private fun createTrainStation(tryTo: String? = null) {
        val trainStationInput = trainStationBuilder.buildTrainStationInput()
        testContext.responseEntity = trainStationsClient.createTrainStation(trainStationInput, tryTo)
        if (tryTo == null) {
            trainStationBuilderHistory.addTrainStationBuilder(trainStationBuilder)
        }
    }

    @Étantdonnée(
        "^(?:l'existence d')?une gare" +
                "( à créer)?" +
                "(?: avec le libellé \"([^\"]*)\")?" +
                "(?: (?:avec|et)? le code \"([^\"]*)\")?$"
    )
    fun uneGare(
        toCreate: String?,
        trainStationLabel: String?,
        trainStationCode: String?
    ) {
        trainStationBuilder.reset()
        if (trainStationLabel != null) {
            trainStationBuilder.withLabel(trainStationLabel)
        }
        if (trainStationCode != null) {
            trainStationBuilder.withCode(trainStationCode)
        }
        if (toCreate == null) {
            createTrainStation()
        }
    }

    @Lorsque("^je( tente de)? créer? cette gare$")
    fun jeCreeCetteGare(tryTo: String?) {
        createTrainStation(tryTo)
    }

    @Alors("cette gare est créée avec succès")
    fun cetteGareEstCreeeAvecSucces() {
        testContext.assertCreated()
    }
}
