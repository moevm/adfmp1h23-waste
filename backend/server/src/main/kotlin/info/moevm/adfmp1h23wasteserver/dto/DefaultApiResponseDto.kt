package info.moevm.adfmp1h23wasteserver.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class DefaultApiResponseDto(

    @JsonProperty("message")
    val message: String,
)
