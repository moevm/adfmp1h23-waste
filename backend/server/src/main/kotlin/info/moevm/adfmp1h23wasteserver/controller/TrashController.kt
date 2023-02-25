package info.moevm.adfmp1h23wasteserver.controller

import info.moevm.adfmp1h23wasteserver.dto.TrashDto
import info.moevm.adfmp1h23wasteserver.service.TrashService
import jakarta.validation.Valid
import org.springframework.data.domain.PageRequest
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
class TrashController(
    private val trashService: TrashService
) {

    @GetMapping(
        value = ["trashes"],
    )
    suspend fun getAll(
        @RequestParam("page") pageIndex: Int,
        @RequestParam("size") pageSize: Int
    ) = trashService.getAll(PageRequest.of(pageIndex, pageSize))

    @GetMapping(
        value = ["trash/{id}"],
    )
    suspend fun get(
        @PathVariable id: String,
    ) = trashService.get(id)

    @PostMapping(
        value = ["trash"],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
    )
    suspend fun saveOrUpdate(
        @Valid @RequestBody trashDto: TrashDto,
    ) = trashService.saveOrUpdate(trashDto)

    @DeleteMapping(
        value = ["trash/{id}"],
    )
    suspend fun delete(@PathVariable id: String) =
        trashService.delete(id)
}
