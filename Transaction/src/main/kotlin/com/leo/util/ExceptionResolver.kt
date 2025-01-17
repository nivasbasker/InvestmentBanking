package com.leo.util

import jakarta.validation.ConstraintViolationException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.core.env.get
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionResolver {

    @Autowired
    private lateinit var environment: Environment

    @ExceptionHandler
    fun validationResolver(ex: MethodArgumentNotValidException): ResponseEntity<String> {

        val errors =
            ex.bindingResult.allErrors.map {
                println(environment.getProperty(it.defaultMessage!!))
                return@map environment.getProperty(it.defaultMessage!!).toString()
            }
        return ResponseEntity(errors.toString(), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler
    fun generalResolver(ex: BankException): ResponseEntity<String> {
        return ResponseEntity(environment.getProperty(ex.message!!), HttpStatus.BAD_REQUEST)
    }

    /*@ExceptionHandler
    fun constraintResolver(ex: ConstraintViolationException): ResponseEntity<String> {
        val errors = ex.constraintViolations.map { it.message }.joinToString { ", " }
        println(errors)
        return ResponseEntity(errors, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler
    fun allExceptionResolver(ex: Exception) {
        println(ex.message)
    }*/
}