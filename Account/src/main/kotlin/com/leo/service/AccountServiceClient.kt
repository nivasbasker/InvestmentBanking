package com.leo.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class AccountServiceClient {

    @Autowired
    private lateinit var webClientBuilder: WebClient.Builder

    //@Value(value = "domain")
    private var domain: String = "http://User/"

    fun isUser(userid: Int): Boolean {
        webClientBuilder.baseUrl(domain).build().get().uri("users/exist/$userid").retrieve().bodyToMono(Boolean::class.java)
            .block()
            ?.let { return it }

        return false

    }
}