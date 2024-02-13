package com.clipboarder.clipboarder.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * TextDto
 *
 * This data class provides the necessary DTO for the text data.
 *
 * @property content The content of the text.
 * @since 1.0.0
 * @author YoungJin Sohn
 */
data class TextDto(
    @SerializedName("content")
    val content: String?
)

/**
 * UploadTextRequestDto
 *
 * This data class provides the necessary DTO for the text upload request.
 *
 * @property userId The user ID of the text uploader.
 * @property content The content of the text.
 * @since 1.0.0
 * @author YoungJin Sohn
 */
data class UploadTextRequestDto(
    @SerializedName("user_id")
    val userId: String?,

    @SerializedName("content")
    val content: String?
)

/**
 * UploadTextResponseDto
 *
 * This data class provides the necessary DTO for the text upload response.
 *
 * @property contentId The content ID of the uploaded text.
 * @since 1.0.0
 * @author YoungJin Sohn
 */
data class UploadTextResponseDto(
    @SerializedName("content_id")
    val contentId: String?
)

/**
 * DownloadTextListResponseDto
 *
 * This data class provides the necessary DTO for the text list download response.
 *
 * @property textList The list of texts.
 * @since 1.0.0
 * @author YoungJin Sohn
 */
data class DownloadTextListResponseDto(
    @SerializedName("result")
    val result: Boolean?,

    @SerializedName("texts")
    val textList: List<TextDto>?
)
