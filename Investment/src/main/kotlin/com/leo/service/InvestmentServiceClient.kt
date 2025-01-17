package com.leo.service

import com.leo.data.AccountDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class InvestmentServiceClient {

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


}