package info.moevm.adfmp1h23wasteserver.service.impl

import info.moevm.adfmp1h23wasteserver.converter.UserEntityToDtoConverter
import info.moevm.adfmp1h23wasteserver.dto.AuthorizeDto
import info.moevm.adfmp1h23wasteserver.dto.RegisterDto
import info.moevm.adfmp1h23wasteserver.dto.UserDto
import info.moevm.adfmp1h23wasteserver.entity.UserEntity
import info.moevm.adfmp1h23wasteserver.repository.ReactiveUserRepository
import info.moevm.adfmp1h23wasteserver.service.UserService
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class DefaultUserService(
    private val reactiveUserRepository: ReactiveUserRepository,
    private val userEntityToDtoConverter: UserEntityToDtoConverter,
) : UserService {

    override suspend fun getAll(pageable: Pageable): Flux<UserDto> =
        reactiveUserRepository
            .findAllByIdNotNull(pageable)
            .map { userEntityToDtoConverter.convert(it) }

    override suspend fun get(email: String): Mono<UserDto> =
        reactiveUserRepository
            .findFirstByEmailNotNull(email)
            .map { userEntityToDtoConverter.convert(it) }

    override suspend fun register(registerDto: RegisterDto): Mono<UserDto> =
        reactiveUserRepository
            .save(UserEntity(null, registerDto.email, registerDto.name, registerDto.passwordHash))
            .map { userEntityToDtoConverter.convert(it) }

    override suspend fun checkAuth(authorizeDto: AuthorizeDto): Mono<Boolean> {
        return reactiveUserRepository
            .findFirstByEmailNotNull(authorizeDto.email)
            .map { it.passwordHash == authorizeDto.passwordHash; }
            .defaultIfEmpty(false)
    }
}
