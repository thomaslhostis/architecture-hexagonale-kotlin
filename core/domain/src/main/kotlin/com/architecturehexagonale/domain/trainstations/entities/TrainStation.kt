package com.architecturehexagonale.domain.trainstations.entities

class TrainStation(
    val code: String,
    val label: String
) {
    companion object {
        private const val CODE_PREFIX = "TRN_"
    }

    fun validate() {
        if (!code.startsWith(CODE_PREFIX)) {
            throw IllegalArgumentException("Le code de la station de train doit commencer par $CODE_PREFIX")
        }
    }
}
