package com.clipboarder.clipboarder.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * SignInDto
 *
 * This object provides the necessary DTO for the sign-in object.
 *
 * @since 1.0.0
 * @author YoungJin
 */
class SignInDto {
    /**
     * SignInRequestDto
     *
     * This data class provides the necessary DTO for the sign-in request.
     *
     * @property email The email of the user.
     * @property password The password of the user.
     * @since 1.0.0
     * @author YoungJin Sohn
     */
    data class SignInRequestDto(
        @SerializedName("email")
        val email: String?,

        @SerializedName("password")
        val password: String?
    )

    /**
     * SignInResponseDto
     *
     * This data class provides the necessary DTO for the sign-in response.
     *
     * @property accessToken The access token of the user.
     * @since 1.0.0
     * @author YoungJin Sohn
     */
    data class SignInResponseDto(
        @SerializedName("access_token")
        val accessToken: String?
    )
}