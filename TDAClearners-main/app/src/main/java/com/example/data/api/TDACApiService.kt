package com.example.data.api

import retrofit2.Response
import retrofit2.http.*

data class ApiResponse<T>(
    val success: Boolean,
    val message: String? = null,
    val data: T? = null,
    val error: ApiErrorDetail? = null
)

data class ApiErrorDetail(
    val message: String
)

data class AuthResponseData(
    val token: String,
    val user: UserDto
)

data class UserDto(
    val id: String,
    val name: String,
    val email: String,
    val role: String,
    val targetSemester: Int = 1,
    val targetYear: Int = 1
)

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String,
    val targetSemester: Int = 1,
    val targetYear: Int = 1
)

data class LoginRequest(
    val email: String,
    val password: String
)

data class AiChatRequest(
    val history: List<ChatTurnDto> = emptyList(),
    val userMessage: String,
    val modelId: String = "gemini-3.5-flash",
    val roleKey: String = "ACADEMIC_PROFESSOR",
    val enableSearchGrounding: Boolean = false
)

data class ChatTurnDto(
    val id: String? = null,
    val role: String,
    val text: String,
    val modelUsed: String? = null,
    val isSearchGrounded: Boolean = false,
    val searchQueries: List<String> = emptyList(),
    val groundingSources: List<GroundingSourceDto> = emptyList()
)

data class GroundingSourceDto(
    val title: String,
    val uri: String
)

data class AiChatResponseData(
    val chatTurn: ChatTurnDto
)

interface TDACApiService {
    @POST("api/auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<ApiResponse<AuthResponseData>>

    @POST("api/auth/login")
    suspend fun login(@Body request: LoginRequest): Response<ApiResponse<AuthResponseData>>

    @GET("api/auth/me")
    suspend fun getMe(): Response<ApiResponse<Map<String, UserDto>>>

    @POST("api/ai/chat")
    suspend fun chatWithAiProxy(@Body request: AiChatRequest): Response<ApiResponse<AiChatResponseData>>
}
