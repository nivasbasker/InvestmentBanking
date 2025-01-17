package com.leo.repo

import com.leo.data.Account
import com.leo.data.SavingsGoal
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface SavingsRepo : JpaRepository<SavingsGoal, Int> {

//    @Query("select c from savings_goal c where c.account.id=?1")
//    fun findByAccountId(accountId : Int) : List<SavingsGoal>

    @Modifying
    @Query("update savings_goal s set s.currentAmount = s.currentAmount + ?1 where s.id = ?2")
    fun contributeSavings(amount : Int, id : Int)
}