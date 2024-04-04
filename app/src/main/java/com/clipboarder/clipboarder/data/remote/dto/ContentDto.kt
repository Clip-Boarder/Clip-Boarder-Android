package com.clipboarder.clipboarder.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * ContentDto
 *
 * This object provides the necessary DTO for the content object.
 *
 * @since 1.0.0
 * @author YoungJin
 */
class ContentDto {
    /**
     * ContentObjectDto
     *
     * This data class provides the necessary DTO for the content object.
     *
     * @property contentId The content ID of the content.
     * @property contentType The type of the content.
     * @property content The content of the content.
     * @since 1.0.0
     * @author YoungJin Sohn
     */
    data class ContentObjectDto(
        @SerializedName("content_id")
        val contentId: String?,

        @SerializedName("type")
        val contentType: String?,

        @SerializedName("content")
        val content: String?
    )

    /**
     * DownloadContentListResponseDto
     *
     * This data class provides the necessary DTO for the content list download response.
     *
     * @property contentList The list of content objects.
     * @since 1.0.0
     * @author YoungJin Sohn
     */
    data class DownloadContentListResponseDto(
        @SerializedName("contents")
        val contentList: List<ContentObjectDto>
    )
}