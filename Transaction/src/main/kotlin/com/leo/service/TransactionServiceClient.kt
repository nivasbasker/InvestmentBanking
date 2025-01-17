package com.leo.service

import com.leo.data.TransactDTO
import com.leo.data.TransactionType
import com.leo.util.BankException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Component
class TransactionServiceClient {

    @Autowired
    private lateinit var webClientBuilder: WebClient.Builder

    //@Value(value = "domain")
    private var userDomain: String = "http://User/"
    private var accDomain: String = "http://Account/"

    fun isUser(userid: Int): Boolean {
        webClientBuilder.baseUrl(userDomain).build().get().uri("users/exist/$userid").retrieve()
            .bodyToMono(Boolean::class.java)
            .block()
            ?.let { return it }

        return false

    }

    fun hasAccount(userid: Int): Boolean {
        webClientBuilder.baseUrl(accDomain).build().get().uri("accounts/check/$userid").retrieve()
            .bodyToMono(Boolean::class.java)
            .block()
            ?.let { return it }

        return false
    }

    fun checkAccountId(accId: Int): Boolean {
        return false
    }

    fun getBalance(accountId: Int): Int {

        return webClientBuilder.baseUrl(accDomain).build().get().uri("accounts/balance/$accountId").retrieve()
            .onStatus({ it.value() == 404 }) {
                throw BankException("Service.NO_SUCH_ACC")
            }
            .bodyToMono(Int::class.java)
            .block()!!
    }

    fun updateBalance(transactDTO: TransactDTO) {
        webClientBuilder.baseUrl(accDomain).build().put().uri("accounts/update").bodyValue(transactDTO).retrieve()
            .toBodilessEntity().block()
    }


}