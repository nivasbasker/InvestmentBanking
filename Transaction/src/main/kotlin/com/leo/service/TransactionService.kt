package com.leo.service

import com.leo.data.*
import com.leo.repo.TransactionRepo
import com.leo.util.BankException
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class TransactionService {

    @Autowired
    private lateinit var transactionRepo: TransactionRepo

    @Autowired
    private lateinit var transactionClient: TransactionServiceClient

    fun makeTransaction(dto: TransactionDTO) {

//        if (!transactionClient.checkAccountId(transactionDTO.accountId))
//            throw BankException("Service.NO_SUCH_ACC")

        if (dto.category == TransactionType.DEBIT) {
            transactionClient.getBalance(dto.accountId).let {
                if (it < dto.amount) throw BankException("Service.LOW_BALANCE")
                continueTransaction(dto)
            }
        } else continueTransaction(dto)


    }

    private fun continueTransaction(dto: TransactionDTO) {
        transactionClient.updateBalance(TransactDTO(dto.accountId, dto.amount, dto.category!!))

        val transaction =
            Transaction(
                null,
                dto.accountId,
                dto.amount,
                dto.description,
                dto.category!!,
                TransactionStatus.COMPLETED,
                LocalDate.now()
            )

        transactionRepo.save(transaction)
    }

    fun getTransactions(accId: Int): List<TransactionDTO> {
        return transactionRepo.getAllByAccountId(accId)
            .map { ModelMapper().map(it, TransactionDTO::class.java) }
    }

    fun scheduleTransaction(dto: TransactionDTO) {

        if (dto.transactionDate == null || dto.transactionDate!!.isBefore(LocalDate.now()))
            throw BankException("Service.INVALID_DATE")

        if (dto.category == TransactionType.DEBIT) {
            transactionClient.getBalance(dto.accountId).let {
                if (it < dto.amount) throw BankException("Service.LOW_BALANCE")
                continueTransaction(dto)
            }
        } else continueTransaction(dto)
    }

}