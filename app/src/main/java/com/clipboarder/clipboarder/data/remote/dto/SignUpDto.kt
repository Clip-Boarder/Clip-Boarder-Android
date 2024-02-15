package com.clipboarder.clipboarder.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * SignUpDto
 *
 * This object provides the necessary DTO for the sign-up object.
 *
 * @since 1.0.0
 * @author YoungJin
 */
class SignUpDto {

    /**
     * SignUpRequestDto
     *
     * This data class provides the necessary DTO for the sign-up request.
     *
     * @property email The email of the user.
     * @property password The password of the user.
     * @property name The name of the user.
     * @property picture The picture of the user.
     * @property provider The provider of the user.
     * @since 1.0.0
     * @author YoungJin Sohn
     */
    data class SignUpRequestDto(
        @SerializedName("email")
        val email: String?,

        @SerializedName("password")
        val password: String?,

        @SerializedName("name")
        val name: String?,

        @SerializedName("picture")
        val picture: String?,

        @SerializedName("provider")
        val provider: String?
    )

    /**
     * SignUpResponseDto
     *
     * This data class provides the necessary DTO for the sign-up response.
     *
     * @property result The result of the sign-up request.
     * @property accessToken The access token of the user.
     * @since 1.0.0
     * @author YoungJin Sohn
     */
    data class SignUpResponseDto(
        @SerializedName("result")
        val result: Boolean?,

        @SerializedName("access_token")
        val accessToken: String?
    )
}