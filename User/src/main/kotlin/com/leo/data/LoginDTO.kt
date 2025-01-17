package com.leo.data

import jakarta.validation.constraints.Pattern
import org.springframework.web.bind.annotation.RequestParam

data class LoginDTO(
    @field:Pattern(regexp = "[A-Za-z]+")
    var userName: String,

    @field:Pattern(regexp = "[A-Za-z0-9]{8,20}")
    var password: String
)
