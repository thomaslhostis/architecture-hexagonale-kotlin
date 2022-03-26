package com.architecturehexagonale.functionaltests.commons

import io.cucumber.java.fr.Alors
import io.cucumber.java8.Fr
import org.springframework.http.HttpStatus

class CommonSteps(
    private val testContext: TestContext
) : Fr {

    @Alors("^je reçois une (?:erreur|réponse) (\\d+)(?: avec le message \"([^\"]*)\")?$")
    fun jeRecoisUnErreur(code: Int, message: String?) {
        val httpStatus = HttpStatus.valueOf(code)
        testContext.assertStatus(httpStatus, message)
    }
}
