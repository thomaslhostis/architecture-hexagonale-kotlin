package com.architecturehexagonale.functionaltests.trainstations.steps

import com.architecturehexagonale.functionaltests.commons.TestContext
import com.architecturehexagonale.functionaltests.trainstations.TrainStationsClient
import com.architecturehexagonale.functionaltests.trainstations.builders.TrainStationBuilder
import io.cucumber.java.fr.Alors
import io.cucumber.java.fr.Lorsque
import io.cucumber.java8.Fr

class SupprimerUneGare(
    private val trainStationBuilder: TrainStationBuilder,
    private val testContext: TestContext,
    private val trainStationsClient: TrainStationsClient
) : Fr {

    @Lorsque("je supprime cette gare")
    fun jeSupprimeCetteGare() {
        TODO("Not yet implemented")
    }

    @Lorsque("^je tente de supprimer la gare \"([^\"]*)\"$")
    fun jeTenteDeSupprimerLaGare(trainStationCode: String) {
        TODO("Not yet implemented")
    }

    @Alors("cette gare est supprimée avec succès")
    fun cetteGareEstSupprimeeAvecSucces() {
        testContext.assertOk()
    }
}
