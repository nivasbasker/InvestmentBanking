package com.leo.controller

import com.leo.data.SavingsGoalDTO
import com.leo.service.AccountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.core.env.get
import org.springframework.core.env.getProperty
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(name = "savings-goal")
class SavingsController {

    @Autowired
    private lateinit var environment: Environment

    @Autowired
    private lateinit var accountService: AccountService

    @PostMapping
    fun creteSavings(@RequestBody savingsGoalDTO: SavingsGoalDTO): ResponseEntity<String> {

        accountService.createSavingsGoal(savingsGoalDTO)

        val response = environment.getProperty("API.SAVINGS_CREATED")
        return ResponseEntity(response, HttpStatus.CREATED)
    }

    @GetMapping("account/{accountId}")
    fun getSavings(@PathVariable accountId: Int): ResponseEntity<List<SavingsGoalDTO>> {

        return ResponseEntity(accountService.getSavingsGoal(accountId), HttpStatus.OK)
    }

    @PutMapping("{goalId}")
    fun contributeToSavings(
        @PathVariable goalId: Int,
        @RequestParam contribution: Int
    ): ResponseEntity<String> {
        accountService.contributeSavings(contribution, goalId)

        val response = environment.getProperty("API.SAVINGS_UPDATED")
        return ResponseEntity(response, HttpStatus.OK)

    }

}