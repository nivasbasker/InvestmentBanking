package com.leo.data

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import lombok.Data


@Data
@Entity(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int,
    var username: String,
    var password: String,
    var email: String,
    var firstName: String,
    var lastName: String
){

    override fun toString(): String {
        return "User(id=$id, username='$username', password='$password', email='$email', firstName='$firstName', lastName='$lastName')"
    }
}