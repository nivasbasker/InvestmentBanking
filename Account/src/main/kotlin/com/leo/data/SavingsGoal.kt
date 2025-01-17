package com.leo.data

import jakarta.persistence.*
import lombok.Data
import java.time.LocalDate

@Entity(name = "savings_goal")
data class SavingsGoal(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int,
    var name: String,
    var targetAmount: Int,
    var targetDate: LocalDate,
    var currentAmount: Int,

//    @ManyToOne
//    @JoinColumn(name = "account_id", referencedColumnName = "id")
//    var account: Account
)