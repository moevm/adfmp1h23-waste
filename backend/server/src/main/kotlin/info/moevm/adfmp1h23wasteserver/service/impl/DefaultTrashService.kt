package info.moevm.adfmp1h23wasteserver.service.impl

import info.moevm.adfmp1h23wasteserver.converter.TrashDtoToEntityConverter
import info.moevm.adfmp1h23wasteserver.converter.TrashEntityToDtoConverter
import info.moevm.adfmp1h23wasteserver.dto.DefaultApiResponseDto
import info.moevm.adfmp1h23wasteserver.dto.TrashDto
import info.moevm.adfmp1h23wasteserver.repository.ReactiveTrashRepository
import info.moevm.adfmp1h23wasteserver.service.TrashService
import org.bson.types.ObjectId
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class DefaultTrashService(
    private val reactiveTrashRepository: ReactiveTrashRepository,
    private val trashDtoToEntityConverter: TrashDtoToEntityConverter,
    private val trashEntityToDtoConverter: TrashEntityToDtoConverter,
) : TrashService {

    override suspend fun getAll(pageable: Pageable): Flux<TrashDto> =
        reactiveTrashRepository
            .findAllByIdNotNull(pageable)
            .map { trashEntityToDtoConverter.convert(it) }

    override suspend fun get(trashId: String): Mono<TrashDto> =
        reactiveTrashRepository
            .findById(ObjectId(trashId))
            .map { trashEntityToDtoConverter.convert(it) }

    override suspend fun saveOrUpdate(trashDto: TrashDto): Mono<TrashDto> =
        reactiveTrashRepository
            .save(trashDtoToEntityConverter.convert(trashDto))
            .map { trashEntityToDtoConverter.convert(it) }

    override suspend fun delete(trashId: String): Mono<DefaultApiResponseDto> =
        reactiveTrashRepository
            .deleteById(ObjectId(trashId))
            .map { DefaultApiResponseDto("Success") }
}
