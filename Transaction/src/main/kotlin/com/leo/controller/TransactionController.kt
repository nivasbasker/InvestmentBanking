package com.leo.controller

import com.leo.data.TransactionDTO
import com.leo.service.TransactionService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("transactions")
class TransactionController {
    @Autowired
    private lateinit var environment: Environment

    @Autowired
    private lateinit var transactionService: TransactionService

    @PostMapping
    fun makeTransaction(@RequestBody @Valid transactionDTO: TransactionDTO): ResponseEntity<String> {

        transactionService.makeTransaction(transactionDTO)

        val response = environment.getProperty("API.MADE_TRANSACTION")

        return ResponseEntity(response, HttpStatus.CREATED)
    }

    @GetMapping("/{accountId}")
    fun getTransactions(@PathVariable accountId: Int): ResponseEntity<List<TransactionDTO>> {
        return ResponseEntity(transactionService.getTransactions(accountId), HttpStatus.OK)
    }

    @PostMapping("/schedule")
    fun scheduleTransaction(@RequestBody @Valid transactionDTO: TransactionDTO): ResponseEntity<String> {

        transactionService.scheduleTransaction(transactionDTO)

        val response = environment.getProperty("API.SCHEDULED_TRANSACTION")

        return ResponseEntity(response, HttpStatus.CREATED)
    }
}