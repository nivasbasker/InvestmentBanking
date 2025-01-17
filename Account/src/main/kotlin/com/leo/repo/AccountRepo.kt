package com.leo.repo

import com.leo.data.Account
import com.leo.data.SavingsGoal
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface AccountRepo : JpaRepository<Account, Int> {


    @Query("select c.savingsGoal from Account c where c.id = ?1")
    fun getSavingsGoalById(id : Int) : List<SavingsGoal>

}