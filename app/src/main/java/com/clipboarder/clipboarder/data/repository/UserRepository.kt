package com.clipboarder.clipboarder.data.repository

import com.clipboarder.clipboarder.data.remote.api.ApiService
import com.clipboarder.clipboarder.data.remote.dto.ApiResponseDto
import com.clipboarder.clipboarder.data.remote.dto.SignInRequestDto
import com.clipboarder.clipboarder.data.remote.dto.SignInResponseDto
import com.clipboarder.clipboarder.data.remote.dto.SignUpRequestDto
import com.clipboarder.clipboarder.data.remote.dto.SignUpResponseDto
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
     * signUp
     *
     * This function signs up the user.
     *
     * @param email The email of the user.
     * @param password The password of the user.
     * @param name The name of the user.
     * @param picture The picture of the user.
     * @param provider The provider of the user.
     * @return The response of the sign up.
     */
    fun signUp(
        email: String,
        password: String,
        name: String,
        picture: String?,
        provider: String
    ): Flow<ApiResponseDto<SignUpResponseDto>> = flow {
        val signUpRequestDto = SignUpRequestDto(email, password, name, picture, provider)
        val response = apiService.signUp(signUpRequestDto)
        if (response.isSuccessful && response.body() != null) {
            emit(response.body()!!)
        } else {
            throw Exception("Sign Up Failed: ${response.message()}")
        }
    }.catch { e ->
        emit(ApiResponseDto(false, null as SignUpResponseDto?))
        throw e
    }

    /**
     * signIn
     *
     * This function signs in the user.
     *
     * @param email The email of the user.
     * @param password The password of the user.
     * @return The response of the sign in.
     */
    fun signIn(email: String, password: String): Flow<ApiResponseDto<SignInResponseDto>> = flow {
        val signInRequestDto = SignInRequestDto(email, password)
        val response = apiService.signIn(signInRequestDto)
        if (response.isSuccessful && response.body() != null) {
            emit(response.body()!!)
        } else {
            throw Exception("Sign In Failed: ${response.message()}")
        }
    }.catch { e ->
        emit(ApiResponseDto(false, null as SignInResponseDto?))
        throw e
    }
}