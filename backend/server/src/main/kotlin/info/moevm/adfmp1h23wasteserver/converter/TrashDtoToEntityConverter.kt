package info.moevm.adfmp1h23wasteserver.converter

import info.moevm.adfmp1h23wasteserver.dto.TrashDto
import info.moevm.adfmp1h23wasteserver.entity.TrashEntity
import org.bson.types.ObjectId
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class TrashDtoToEntityConverter : Converter<TrashDto, TrashEntity> {

    override fun convert(source: TrashDto) =
        TrashEntity(
            id = source.id?.let { ObjectId(it) },
            name = source.name,
            category = source.category,
        )
}
