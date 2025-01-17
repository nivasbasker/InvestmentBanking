package com.leo.data

import jakarta.validation.Valid
import jakarta.validation.constraints.Min
import java.time.LocalDate

data class TransactionDTO(

    var id: Int?,

    var accountId: Int,

    @field:Min(value = 1)
    var amount: Int,
    var description: String,

    @field:Valid
    var category: TransactionType?,
    var status: TransactionStatus?,

    var transactionDate: LocalDate?
) {
    constructor() : this(null, 0, 0, "", null, null, null)
}

