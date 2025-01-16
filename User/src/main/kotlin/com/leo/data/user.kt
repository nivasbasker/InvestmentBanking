package com.leo.data

import jakarta.persistence.Entity
import jakarta.persistence.Id
import lombok.Data


@Data
@Entity(name = "users")
data class User(
    @Id
    val id: Int,
    val username: String,
    val password: String,
    val email: String,
    val firstName: String,
    val lastName: String
)