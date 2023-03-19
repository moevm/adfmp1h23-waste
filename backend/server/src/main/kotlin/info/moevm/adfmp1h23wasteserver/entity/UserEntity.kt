package info.moevm.adfmp1h23wasteserver.entity

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document("user")
class UserEntity(

    @Id
    var id: ObjectId? = null,

    @Field("email")
    var email: String,

    @Field("name")
    var name: String,

    @Field("passwordHash")
    var passwordHash: String,
)
