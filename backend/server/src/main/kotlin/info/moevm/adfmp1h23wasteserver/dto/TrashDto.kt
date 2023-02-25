package info.moevm.adfmp1h23wasteserver.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import info.moevm.adfmp1h23wasteserver.entity.Category

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class TrashDto(

    @JsonProperty("id")
    var id: String? = null,

    @JsonProperty("name")
    var name: String,

    @JsonProperty("category")
    var category: Category,
)
