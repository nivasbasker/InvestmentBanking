package com.leo.data

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Pattern
import lombok.Data
import lombok.NoArgsConstructor

data class UserDTO(

    var id: Int?,

    @field:Pattern(regexp = "[A-Za-z]+", message = "user.username.invalid")
    var username: String,

    @field:Pattern(regexp = "[A-Za-z0-9]{8,}", message = "user.password.invalid")
    var password: String,

    @field:Email(message = "user.email.invalid")
    var email: String,

    @field:Pattern(regexp = "[A-Z][a-z\\s]+", message = "user.fname.invalid")
    var firstName: String,

    @field:NotEmpty(message = "user.lname.invalid")
    var lastName: String
) {
    constructor() : this(null, "", "", "", "", "")
}
