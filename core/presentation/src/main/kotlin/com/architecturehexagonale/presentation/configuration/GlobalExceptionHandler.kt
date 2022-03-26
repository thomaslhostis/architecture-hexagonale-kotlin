package com.architecturehexagonale.presentation.configuration

import com.architecturehexagonale.domain.exceptions.NotFoundException
import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
// Cette classe permet de centraliser la gestion des exceptions. Toutes les exceptions
// sont interceptées ici. Important : les exceptions doivent étendre directement ou
// indirectement `RuntimeException`. Cela permet d'éviter de se retrouver avec une
// `UndeclaredThrowableException` qui serait interceptée par la méthode `handleThrowable`
// qui renvoie systématiquement une erreur 503 => https://stackoverflow.com/a/5490372
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(notFoundException: NotFoundException): ResponseEntity<*> {
        return ResponseEntity.status(NOT_FOUND).body(notFoundException.message)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(illegalArgumentException: IllegalArgumentException): ResponseEntity<*> {
        return ResponseEntity.status(BAD_REQUEST).body(illegalArgumentException.message)
    }

    @ExceptionHandler(MissingKotlinParameterException::class)
    fun handleMethodArgumentNotValid(exception: MissingKotlinParameterException): ResponseEntity<*> {
        val errors = ArrayList<String>()
        for (err in exception.path) {
            errors.add(err.fieldName)
        }
        return ResponseEntity.status(BAD_REQUEST)
            .body("Missing parameters: " + errors.joinToString(", ", "\"", "\""))
    }

    // Exceptions non gérées
    @ExceptionHandler(Throwable::class)
    fun handleThrowable(throwable: Throwable): ResponseEntity<*> {
        return ResponseEntity
            .status(INTERNAL_SERVER_ERROR)
            // Par mesure de précaution on ne retourne pas le message
            // d'erreur car il peut contenir des informations sensibles
            .body(INTERNAL_SERVER_ERROR)
    }

    // Il arrive que certaines erreurs soient générées par le framework
    // Spring Boot et ne soient pas interceptées par l'une des méthodes
    // précédentes (cela inclut `handleThrowable`). Par exemple lorsqu'on
    // essaie de retourner `ResponseEntity.ok()` avec le type de retour
    // `ResponseEntity.BodyBuilder`. Cette méthode permet d'avoir plus de
    // visibilité dans ce cas de figure.
    override fun handleExceptionInternal(
        exception: java.lang.Exception,
        body: Any?,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        return super.handleExceptionInternal(exception, body, headers, status, request)
    }
}
