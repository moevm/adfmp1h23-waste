package info.moevm.adfmp1h23wasteserver.service

import info.moevm.adfmp1h23wasteserver.dto.AuthorizeDto
import info.moevm.adfmp1h23wasteserver.dto.RegisterDto
import info.moevm.adfmp1h23wasteserver.dto.UserDto
import org.springframework.data.domain.Pageable
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface UserService {

    suspend fun getAll(pageable: Pageable): Flux<UserDto>

    suspend fun get(email: String): Mono<UserDto>

    suspend fun register(registerDto: RegisterDto): Mono<UserDto>

    suspend fun checkAuth(authorizeDto: AuthorizeDto): Boolean
}
