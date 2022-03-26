package com.architecturehexagonale.functionaltests.commons

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import kotlin.reflect.KClass
import kotlin.test.assertEquals

@Component
class TestContext {

    lateinit var responseEntity: ResponseEntity<*>

    companion object {
        fun getResponseType(tryTo: String?, defaultResponseType: KClass<*>): Class<*> {
            return if (tryTo == null) defaultResponseType.java else String::class.java
        }
    }

    fun <T : Any> getResponseBody(responseType: KClass<out T>): T {
        return responseType.javaObjectType.cast(responseEntity.body)
    }

    fun assertOk(message: String? = null) {
        assertStatus(OK, message)
    }

    fun assertCreated(message: String? = null) {
        assertStatus(CREATED, message)
    }

    fun assertStatus(httpStatus: HttpStatus, message: String? = null) {
        assertEquals(httpStatus, responseEntity.statusCode)
        if (message != null) {
            assertEquals(message, responseEntity.body.toString())
        }
    }
}
