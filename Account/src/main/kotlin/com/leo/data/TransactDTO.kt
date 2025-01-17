package com.leo.data

data class TransactDTO(
    var accountId: Int,
    var amount: Int,
    var category: TransactionType
)

enum class TransactionType {
    CREDIT,
    DEBIT
}
