package com.leo.data

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.validation.Valid
import jakarta.validation.constraints.Min

data class InvestmentDTO(
    var id: Int?,
    var userId: Int,

    @field:Valid
    var type: InvestmentType,
    var currentValue: Int,

    @field:Min(value = 1)
    var initialInvestment: Int
) {
    constructor() : this(null, 0, InvestmentType.SAVINGS, 0, 0)
}

