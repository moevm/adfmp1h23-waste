package info.moevm.adfmp1h23wasteserver.entity

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document("trash")
class TrashEntity(

    @Id
    var id: ObjectId? = null,

    @Field("name")
    var name: String,

    @Field("category")
    var category: Category,
)
