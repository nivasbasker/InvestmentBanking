package com.leo.data

import jakarta.persistence.*
import java.time.LocalDate

@Entity
data class Transaction(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int?,

    var accountId: Int,
    var amount: Int,
    var description: String,
    @Enumerated(EnumType.STRING)
    var category: TransactionType,
    @Enumerated(EnumType.STRING)
    var status: TransactionStatus,
    var transactionDate: LocalDate
)

enum class TransactionType {
    CREDIT,
    DEBIT
}

enum class TransactionStatus {
    SCHEDULED,
    COMPLETED
}

