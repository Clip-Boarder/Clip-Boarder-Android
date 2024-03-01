package com.clipboarder.clipboarder.data.repository

import android.content.SharedPreferences
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
class UserRepository @Inject constructor(
    private val apiService: ApiService,
    private val sharedPreferences: SharedPreferences
) {
    /**
     * signIn
     *
     * This function signs in the user.
     *
     * @param googleIdToken The Google id token of the user.
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


    /**
     * saveLoginInfo
     *
     * This function saves the login information to the shared preferences.
     *
     * @param accessToken The access token.
     * @param refreshToken The refresh token.
     * @param email The email of the user.
     */
    fun saveLoginInfo(accessToken: String, refreshToken: String, email: String) {
        sharedPreferences.edit().putString("accessToken", accessToken).apply()
        sharedPreferences.edit().putString("refreshToken", refreshToken).apply()
        sharedPreferences.edit().putString("email", email).apply()
    }

    /**
     * getAccessToken
     *
     * This function gets the access token from the shared preferences.
     *
     * @return The access token.
     */
    fun getAccessToken(): String? {
        return sharedPreferences.getString("accessToken", null)
    }

    /**
     * getRefreshToken
     *
     * This function gets the refresh token from the shared preferences.
     *
     * @return The refresh token.
     */
    fun getRefreshToken(): String? {
        return sharedPreferences.getString("refreshToken", null)
    }

    /**
     * getEmail
     *
     * This function gets the email from the shared preferences.
     *
     * @return The email.
     */
    fun getEmail(): String? {
        return sharedPreferences.getString("email", null)
    }
}