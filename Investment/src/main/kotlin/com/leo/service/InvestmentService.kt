package com.leo.service

import com.leo.data.Investment
import com.leo.data.InvestmentDTO
import com.leo.repo.InvestmentRepo
import com.leo.util.BankException
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.Modifying
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class InvestmentService {
    @Autowired
    private lateinit var investmentRepo: InvestmentRepo

    @Autowired
    private lateinit var investmentClient: InvestmentServiceClient

    @Modifying
    @Transactional
    fun startInvestment(investmentDTO: InvestmentDTO) {
        if (investmentDTO.initialInvestment <= 0) throw BankException("Service.INVALID_INVESTMENT")

        if (!investmentClient.isUser(investmentDTO.userId)) throw BankException("Service.NO_SUCH_USER")

        investmentRepo.save(ModelMapper().map(investmentDTO, Investment::class.java))
    }

    @Transactional
    fun getInvestments(userId: Int): List<InvestmentDTO> {

        if (!investmentClient.hasAccount(userId)) throw BankException("Service.NO_ACCOUNTS")

        val list = investmentRepo.getAllByUserId(userId).map { ModelMapper().map(it, InvestmentDTO::class.java) }

        if (list.isEmpty()) throw BankException("Service.NO_INVESTMENTS")

        return list
    }

    @Transactional
    fun contribute(userId: Int, investmentId: Int, investmentAmount: Int) {
        if (investmentAmount <= 0) throw BankException("Service.INVALID_INVESTMENT")

        investmentRepo.contributeToInvestment(investmentId, investmentAmount)

    }
}