package com.leo.controller

import com.leo.data.LoginDTO
import com.leo.data.UserDTO
import com.leo.service.UserService
import jakarta.validation.Valid
import jakarta.validation.constraints.Pattern
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("users")
@Validated
class UserController {

    @Autowired
    lateinit var environment: Environment

    @Autowired
    lateinit var service: UserService

    @PostMapping("register")
    fun registerUser(@Valid @RequestBody user: UserDTO): ResponseEntity<String> {

        val id = service.registerUser(user);

        val response = environment.getProperty("API.REGISTER_SUCCESS") + "with userId : $id"
        return ResponseEntity(response, HttpStatus.CREATED)
    }

    @PostMapping("login")
    fun loginUser(@RequestBody @Valid loginDTO: LoginDTO): ResponseEntity<String> {

        service.login(loginDTO);

        val response = environment.getProperty("API.LOGIN_SUCCESS")
        return ResponseEntity(response, HttpStatus.OK)
    }

    @GetMapping("exist/{id}")
    fun isUser(@PathVariable id: Int): ResponseEntity<Boolean> {
        return ResponseEntity(service.isUser(id), HttpStatus.OK)
    }


}