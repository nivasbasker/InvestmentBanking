package com.leo.data

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Pattern
import lombok.Data

@Data
data class UserDTO(

    val id: Int,

    @Pattern(regexp = "[A-Za-z]+",message = "{user.username.invalid}")
    val username: String,

    @Pattern(regexp = "[A-Za-z0-9]{8,}",message = "{user.password.invalid}")
    val password: String,

    @Email(message = "{user.email.invalid}")
    val email: String,

    @Pattern(regexp = "[A-Za-z]+",message = "{user.fname.invalid}")
    val firstName: String,

    @NotEmpty(message = "{user.lname.invalid}")
    val lastName: String
)
