package info.moevm.adfmp1h23wasteserver.repository

import info.moevm.adfmp1h23wasteserver.entity.TrashEntity
import org.bson.types.ObjectId
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Flux

interface ReactiveTrashRepository : ReactiveMongoRepository<TrashEntity, ObjectId> {

    fun findAllByIdNotNull(pageable: Pageable): Flux<TrashEntity>
}
