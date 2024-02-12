package com.clipboarder.clipboarder.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * ApiService
 *
 * This interface provides the necessary API endpoints for the application.
 *
 * @param T The type of the data to be returned from the API.
 * @property result The result of the API request.
 * @property data The data returned from the API request.
 * @since 1.0.0
 * @author YoungJin Sohn
 */
data class ApiResponseDto<T>(
    @SerializedName("result")
    val result: Boolean?,

    @SerializedName("data")
    val data: T?
)
