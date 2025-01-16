package repo

import data.Account
import data.SavingsGoal
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface SavingsRepo : JpaRepository<SavingsGoal, Int> {

    fun findByAccountId(accountId : Int) : List<SavingsGoal>

    @Query("update savings_goal s set s.balance = s.balance + ?1 where s.id = ?2")
    fun contributeSavings(amount : Int, id : Int)
}