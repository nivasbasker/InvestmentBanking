package com.leo.repo

import com.leo.data.Transaction
import org.springframework.data.jpa.repository.JpaRepository

interface TransactionRepo : JpaRepository<Transaction, Int> {

    fun getAllByAccountId(accountId: Int): List<Transaction>
}