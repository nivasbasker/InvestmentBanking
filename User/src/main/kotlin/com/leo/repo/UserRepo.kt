package com.leo.repo

import com.leo.data.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserRepo : JpaRepository<User,Int> {

    fun findByUsername(userName : String) : Optional<User>
}