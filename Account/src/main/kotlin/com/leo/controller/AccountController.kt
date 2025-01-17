package com.leo.controller

import com.leo.data.AccountDTO
import com.leo.data.TransactDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import com.leo.service.AccountService
import jakarta.validation.Valid
import jakarta.validation.constraints.Min
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam

@RequestMapping("accounts")
@RestController
@Validated
class AccountController {
    @Autowired
    private lateinit var accountService: AccountService

    @Autowired
    private lateinit var environment: Environment

    @PostMapping()
    fun createAccount(@RequestParam userId: Int, @Min(value = 1) @RequestParam balance: Int): ResponseEntity<String> {
        val num = accountService.createAccount(userId, balance)
        val response = environment.getProperty("API.REGISTER_SUCCESS") + " with account number : $num"
        return ResponseEntity(response, HttpStatus.CREATED)

    }

    @GetMapping("/{accId}")
    fun getAccount(@PathVariable accId: Int): ResponseEntity<AccountDTO> {

        return ResponseEntity(accountService.getAccount(accId), HttpStatus.OK)
    }

    @GetMapping("/balance/{accId}")
    fun getBalance(@PathVariable accId: Int): ResponseEntity<Int> {

        return ResponseEntity(accountService.getBalance(accId), HttpStatus.OK)
    }

    @PutMapping("update")
    fun updateAccount(@RequestBody @Valid dto: TransactDTO): ResponseEntity<String> {

        accountService.updateAccount(dto)

        val response = environment.getProperty("API.UPDATE_SUCCESS")
        return ResponseEntity(response, HttpStatus.OK)
    }

    @GetMapping("/check/{userId}")
    fun hasAccount(@PathVariable userId: Int): ResponseEntity<Boolean> {
        return ResponseEntity(accountService.hasAccount(userId), HttpStatus.OK)
    }


}