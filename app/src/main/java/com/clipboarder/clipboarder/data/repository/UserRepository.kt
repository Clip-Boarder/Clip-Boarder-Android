package com.clipboarder.clipboarder.data.repository

import com.clipboarder.clipboarder.data.remote.api.ApiService
import com.clipboarder.clipboarder.data.remote.dto.ApiResponseDto
import com.clipboarder.clipboarder.data.remote.dto.SignInDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * UserRepository
 *
 * This class provides the necessary repository for the user data.
 *
 * @since 1.0.0
 * @author YoungJin Sohn
 */
class UserRepository @Inject constructor(private val apiService: ApiService) {
    /**
     * signIn
     *
     * This function signs in the user.
     *
     * @param email The email of the user.
     * @param password The password of the user.
     * @return The response of the sign in.
     */
    fun signIn(googleIdToken: String): Flow<ApiResponseDto<SignInDto.SignInResponseDto>> =
        flow {
            val signInRequestDto = SignInDto.SignInRequestDto(googleIdToken)
            val response = apiService.signIn(signInRequestDto)
            if (response.isSuccessful && response.body() != null) {
                emit(response.body()!!)
            } else {
                throw Exception("Sign In Failed: ${response.message()}")
            }
        }.catch { e ->
            emit(ApiResponseDto(false, null as SignInDto.SignInResponseDto?))
            throw e
        }
}