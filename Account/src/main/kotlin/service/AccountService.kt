package service

import data.Account
import data.SavingsGoal
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import repo.AccountRepo
import repo.SavingsRepo
import util.BankException
import java.util.Optional

@Service
class AccountService {

    @Autowired
    private lateinit var accountRepo: AccountRepo

    @Autowired
    private lateinit var savingsRepo: SavingsRepo

    @Autowired
    private lateinit var accountClient: AccountServiceClient

    fun createAccount(account: Account): String {

        if (account.balance <= 0) throw BankException("")

        if (!accountClient.isUser(account.userId)) throw BankException("")

        return accountRepo.save(account).accountNumber;
    }

    fun getAccount(id: Int): Account {
        val opt = accountRepo.findById(id)
        val account = opt.orElseThrow { BankException("") }
        return account
    }

    fun createSavingsGoal(savingsGoal: SavingsGoal) {
        accountRepo.findById(897).orElseThrow { BankException("") }
        savingsRepo.save(savingsGoal)
    }

    fun getSavingsGoal(id: Int): List<SavingsGoal> {
        val savings = savingsRepo.findByAccountId(id)

        if (savings.isEmpty()) throw BankException("")

        return savings
    }

    fun contributeSavings(amount: Int, id: Int) {

        if (amount <= 0) throw BankException("")
        savingsRepo.findById(id).orElseThrow { BankException("") }

        savingsRepo.contributeSavings(amount, id)
    }
}