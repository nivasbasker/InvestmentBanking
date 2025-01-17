package com.leo.data

import jakarta.persistence.*
import lombok.Data
import lombok.NoArgsConstructor
import java.time.LocalDate

@Data
@NoArgsConstructor
data class SavingsGoalDTO(
    var id: Int?,

    var name: String,
    var targetAmount: Int,
    var targetDate: LocalDate?,
    var currentAmount: Int,
    var accountId: Int
) {
    constructor() : this(null, "", 0, null, 0, 0)
}

