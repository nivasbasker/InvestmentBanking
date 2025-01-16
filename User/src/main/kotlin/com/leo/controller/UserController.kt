package com.leo.controller

import com.leo.data.UserDTO
import com.leo.service.UserService
import jakarta.validation.Valid
import jakarta.validation.constraints.Pattern
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("users")
@Validated
class UserController {

    @Autowired
    lateinit var environment: Environment

    @Autowired
    lateinit var service: UserService

    @PostMapping("/register")
    fun resisterUser(@RequestBody @Valid user: UserDTO): ResponseEntity<String> {

        service.registerUser(user);

        val response = environment.getProperty("API.REGISTER_SUCCESS")
        return ResponseEntity(response, HttpStatus.OK)
    }

    @RequestMapping("/login")
    fun loginUser(@RequestParam @Pattern(regexp = "[A-Za-z]+") userName : String, @RequestParam @Pattern(regexp = "[A-Za-z0-9]{8,20}")password : String): ResponseEntity<String> {

        service.login(userName,password);

        val response = environment.getProperty("API.LOGIN_SUCCESS")
        return ResponseEntity(response, HttpStatus.OK)
    }


}