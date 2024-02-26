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
     * @property googleIdToken The Google id token of the user.
     * @since 1.0.0
     * @author YoungJin Sohn
     */
    data class SignInRequestDto(
        @SerializedName("google_id_token")
        val googleIdToken: String?,
    )

    /**
     * SignInResponseDto
     *
     * This data class provides the necessary DTO for the sign-in response.
     *
     * @property accessToken The access token of the user.
     * @property refreshToken The refresh token of the user.
     * @since 1.0.0
     * @author YoungJin Sohn
     */
    data class SignInResponseDto(
        @SerializedName("access_token")
        val accessToken: String?,

        @SerializedName("refresh_token")
        val refreshToken: String?
    )
}