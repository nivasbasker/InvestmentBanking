package com.leo.data

import jakarta.persistence.*
import org.springframework.data.annotation.Id

@Entity
data class Investment(

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int,
    var userId: Int,
    @field:Enumerated(EnumType.STRING)
    var type: InvestmentType,
    var currentValue: Int,
    var initialInvestment: Int
)

enum class InvestmentType {
    SAVINGS,
    RETIREMENT,
    EDUCATION
}
