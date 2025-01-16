package controller

import data.Account
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import service.AccountService

@RequestMapping("accounts")
@RestController
class AccountController {
    @Autowired
    private lateinit var accountService: AccountService

    @Autowired
    private lateinit var environment: Environment

    @PostMapping()
    fun createAccount(account: Account): ResponseEntity<String> {
        accountService.createAccount(account)

        val response = environment.getProperty("API.REGISTER_SUCCESS")
        return ResponseEntity(response, HttpStatus.CREATED)

    }

    @GetMapping("/{id}")
    fun getAccount(@PathVariable id: Int): ResponseEntity<Account> {

        return ResponseEntity(accountService.getAccount(id), HttpStatus.OK)
    }
}