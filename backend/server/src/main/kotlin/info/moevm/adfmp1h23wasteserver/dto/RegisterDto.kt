package info.moevm.adfmp1h23wasteserver.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.Email

class RegisterDto(

    @JsonProperty("name")
    var name: String,

    @JsonProperty("email")
    @field:Email
    var email: String,

    @JsonProperty("password_hash")
    var passwordHash: String,
)
