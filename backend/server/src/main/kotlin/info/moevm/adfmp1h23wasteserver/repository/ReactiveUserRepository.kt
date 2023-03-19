package info.moevm.adfmp1h23wasteserver.repository

import info.moevm.adfmp1h23wasteserver.entity.UserEntity
import org.bson.types.ObjectId
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ReactiveUserRepository : ReactiveMongoRepository<UserEntity, ObjectId> {

    fun findAllByIdNotNull(pageable: Pageable): Flux<UserEntity>

    fun findFirstByEmailNotNull(email: String): Mono<UserEntity>
}
