package com.leo.data

import lombok.Data

@Data
data class UserDTO(
    val id: Int,
    val username: String,
    val password: String,
    val email: String,
    val firstName: String,
    val lastName: String
)
