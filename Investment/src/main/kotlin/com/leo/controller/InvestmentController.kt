package com.leo.controller

import com.leo.data.InvestmentDTO
import com.leo.service.InvestmentService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("investments")
class InvestmentController {

    @Autowired
    private lateinit var investmentService: InvestmentService

    @Autowired
    private lateinit var environment: Environment

    @PostMapping()
    fun startInvestment(
        @RequestBody @Valid investmentDTO: InvestmentDTO,
    ): ResponseEntity<String> {

        investmentService.startInvestment(investmentDTO)

        val response = environment.getProperty("API.REGISTER_SUCCESS")

        return ResponseEntity(response, HttpStatus.CREATED)
    }

    @GetMapping("/user/{userId}")
    fun getInvestments(@PathVariable userId: Int): ResponseEntity<List<InvestmentDTO>> {
        return ResponseEntity(investmentService.getInvestments(userId), HttpStatus.OK)
    }

    @PutMapping("/{userId}/{investmentId}/{investmentAmount}")
    fun contribute(
        @PathVariable userId: Int,
        @PathVariable investmentId: Int,
        @PathVariable investmentAmount: Int
    ): ResponseEntity<String> {

        investmentService.contribute(userId, investmentId, investmentAmount)
        val response = environment.getProperty("API.UPDATE_SUCCESS")

        return ResponseEntity(response, HttpStatus.CREATED)
    }
}