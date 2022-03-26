package com.architecturehexagonale.functionaltests.commons

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule

object TestJackson {
    fun write(any: Any): String {
        return ObjectMapper()
            // Les deux prochaines lignes permettent de sérialiser
            // les dates au format ISO au lieu du format timestamp
            .registerModule(JavaTimeModule())
            .disable(WRITE_DATES_AS_TIMESTAMPS)
            .writeValueAsString(any)
    }
}
