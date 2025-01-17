package com.leo.repo

import com.leo.data.Investment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface InvestmentRepo : JpaRepository<Investment, Int> {

    fun getAllByUserId(userId: Int): List<Investment>

    @Modifying
    @Query("update Investment i set i.currentValue = i.currentValue + ?2 where i.id = ?1 ")
    fun contributeToInvestment(investmentId: Int, investmentAmount: Int)
}