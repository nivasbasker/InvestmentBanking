package com.leo.service

import com.leo.data.LoginDTO
import com.leo.data.User
import com.leo.data.UserDTO
import com.leo.repo.UserRepo
import com.leo.util.BankException
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class UserService {
    @Autowired
    lateinit var userRepo: UserRepo

    fun registerUser(user: UserDTO): Int {

        val opt = userRepo.findByUsername(user.username)

        if (opt.isPresent) throw BankException("Service.USERNAME_EXISTS")

        return userRepo.save(ModelMapper().map(user, User::class.java)).id

    }

    fun login(loginDTO: LoginDTO) {
        val opt = userRepo.findByUsername(loginDTO.userName)

        if (opt.isEmpty) throw BankException("Service.NO_SUCH_USER")

        if (!opt.get().password.equals(loginDTO.password)) throw BankException("Service.INVALID_CRED")

    }

    fun isUser(id: Int): Boolean {
        return userRepo.findById(id).isPresent
    }
}