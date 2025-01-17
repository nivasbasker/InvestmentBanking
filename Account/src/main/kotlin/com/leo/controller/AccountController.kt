package com.leo.controller

import com.leo.data.Account
import com.leo.data.AccountDTO
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
import jakarta.validation.constraints.Min
import org.springframework.validation.annotation.Validated
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

    @GetMapping("/{id}")
    fun getAccount(@PathVariable id: Int): ResponseEntity<AccountDTO> {

        return ResponseEntity(accountService.getAccount(id), HttpStatus.OK)
    }

//    @GetMapping("/{userId}")
//    fun getAccounts(@PathVariable userId: Int): ResponseEntity<List<AccountDTO>> {
//        return ResponseEntity(accountService.getAccounts(userId), HttpStatus.OK)
//    }

    @GetMapping("/check/{userId}")
    fun hasAccount(@PathVariable userId: Int): ResponseEntity<Boolean> {
        return ResponseEntity(accountService.hasAccount(userId), HttpStatus.OK)
    }


}