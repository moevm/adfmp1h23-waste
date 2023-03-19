package info.moevm.adfmp1h23wasteserver.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.Email

class AuthorizeDto(

    @JsonProperty("email")
    @field:Email
    var email: String,

    @JsonProperty("password_hash")
    var passwordHash: String,
)
