package com.leo.service

import com.leo.data.Account
import com.leo.data.AccountDTO
import com.leo.data.SavingsGoal
import com.leo.data.SavingsGoalDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.leo.repo.AccountRepo
import com.leo.repo.SavingsRepo
import com.leo.util.BankException
import org.modelmapper.ModelMapper
import org.springframework.data.jpa.repository.Modifying
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.RequestParam
import java.time.LocalDate


@Service
class AccountService {

    @Autowired
    private lateinit var accountRepo: AccountRepo

    @Autowired
    private lateinit var savingsRepo: SavingsRepo

    @Autowired
    private lateinit var accountClient: AccountServiceClient

    @Transactional
    @Modifying
    fun createAccount(userId: Int, balance: Int): String {

        if (balance <= 0) throw BankException("Service.LOW_BALANCE")

        if (!accountClient.isUser(userId)) throw BankException("Service.NO_SUCH_USER")

        val ac = Account()
        ac.let {
            it.balance = balance
            it.userId = userId
            it.isActive = true
        }

        accountRepo.save(ac)
        ac.accountNumber = "ACC${ac.id}"

        return ac.accountNumber
    }

    @Transactional
    fun getAccount(id: Int): AccountDTO {
        val opt = accountRepo.findById(id)
        val account = opt.orElseThrow { BankException("Service.INVALID_ACC") }
        return ModelMapper().map(account, AccountDTO::class.java)
    }

    @Transactional
    fun createSavingsGoal(savingsGoal: SavingsGoalDTO) {

        val acc = accountRepo.findById(savingsGoal.accountId).orElseThrow { BankException("Service.INVALID_ACC") }
        val sav = ModelMapper().map(savingsGoal, SavingsGoal::class.java)
        acc.savingsGoal = acc.savingsGoal?.apply { add(sav) } ?: mutableListOf(sav)

    }

    @Transactional
    fun getSavingsGoal(accountId: Int): List<SavingsGoalDTO> {

        accountRepo.findById(accountId).orElseThrow { BankException("Service.INVALID_ACC") }

        val savings = accountRepo.getSavingsGoalById(accountId)

        if (savings.isEmpty()) throw BankException("Service.NO_SAVINGS_GOAL_YET")

        return savings.map { ModelMapper().map(it, SavingsGoalDTO::class.java) }
    }

    @Transactional
    @Modifying
    fun contributeSavings(contribution: Int, id: Int) {

        if (contribution <= 0) throw BankException("Service.LOW_BALANCE")
        savingsRepo.findById(id).orElseThrow { BankException("Service.NO_SUCH_SAVINGS") }

        savingsRepo.contributeSavings(contribution, id)
    }
}