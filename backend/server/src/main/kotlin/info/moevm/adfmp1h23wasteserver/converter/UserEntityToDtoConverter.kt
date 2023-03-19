package info.moevm.adfmp1h23wasteserver.converter

import info.moevm.adfmp1h23wasteserver.dto.UserDto
import info.moevm.adfmp1h23wasteserver.entity.UserEntity
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class UserEntityToDtoConverter : Converter<UserEntity, UserDto> {

    override fun convert(source: UserEntity) =
        UserDto(
            name = source.name,
            email = source.email
        )
}
