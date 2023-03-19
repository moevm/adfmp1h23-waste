package info.moevm.adfmp1h23wasteserver.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.Email

class UserDto(

    @JsonProperty("name")
    var name: String,

    @JsonProperty("email")
    @field:Email
    var email: String,
)
