package info.moevm.adfmp1h23wasteserver.converter

import info.moevm.adfmp1h23wasteserver.dto.TrashDto
import info.moevm.adfmp1h23wasteserver.entity.TrashEntity
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class TrashEntityToDtoConverter : Converter<TrashEntity, TrashDto> {

    override fun convert(source: TrashEntity) =
        TrashDto(
            id = source.id!!.toHexString(),
            name = source.name,
            category = source.category,
        )
}
