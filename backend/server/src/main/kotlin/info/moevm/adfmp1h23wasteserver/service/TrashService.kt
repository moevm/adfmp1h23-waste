package info.moevm.adfmp1h23wasteserver.service

import info.moevm.adfmp1h23wasteserver.dto.DefaultApiResponseDto
import info.moevm.adfmp1h23wasteserver.dto.TrashDto
import org.springframework.data.domain.Pageable
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface TrashService {

    suspend fun getAll(pageable: Pageable): Flux<TrashDto>

    suspend fun get(trashId: String): Mono<TrashDto>

    suspend fun saveOrUpdate(trashDto: TrashDto): Mono<TrashDto>

    suspend fun delete(trashId: String): Mono<DefaultApiResponseDto>
}
