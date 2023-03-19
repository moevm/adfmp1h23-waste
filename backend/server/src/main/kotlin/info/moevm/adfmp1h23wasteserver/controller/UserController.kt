package info.moevm.adfmp1h23wasteserver.controller

import info.moevm.adfmp1h23wasteserver.dto.AuthorizeDto
import info.moevm.adfmp1h23wasteserver.dto.RegisterDto
import info.moevm.adfmp1h23wasteserver.service.UserService
import jakarta.validation.Valid
import org.springframework.data.domain.PageRequest
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
class UserController(
    private val userService: UserService
) {

    @GetMapping(
        value = ["users"],
    )
    suspend fun getAll(
        @RequestParam("page") pageIndex: Int,
        @RequestParam("size") pageSize: Int
    ) = userService.getAll(PageRequest.of(pageIndex, pageSize))

    @GetMapping(
        value = ["user/{email}"],
    )
    suspend fun get(
        @PathVariable email: String,
    ) = userService.get(email)

    @PostMapping(
        value = ["user/register"],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
    )
    suspend fun register(
        @Valid @RequestBody registerDto: RegisterDto,
    ) = userService.register(registerDto)

    @PostMapping(
        value = ["user/auth"],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
    )
    suspend fun checkAuth(
        @Valid @RequestBody authorizeDto: AuthorizeDto,
    ) = userService.checkAuth(authorizeDto)
}
