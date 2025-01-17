package com.leo.data

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import lombok.Data
import java.time.LocalDate

data class AccountDTO(
    var id: Int?,

    var userId: Int,
    var accountNumber: String?,
    var balance: Int,
    var createdAt: LocalDate?,
    var updatedAt: LocalDate?,
    var isActive: Boolean?,

    var savingsGoal: MutableList<SavingsGoal>?
) {
    constructor() : this(null, 0, "", 0, null, null, false, null)
}
