package com.clipboarder.clipboarder.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * ImageDto
 *
 * This object provides the necessary DTO for the image object.
 *
 * @since 1.0.0
 * @author YoungJin
 */
class ImageDto {
    /**
     * ImageObjectDto
     *
     * This data class provides the necessary DTO for the image object.
     *
     * @property content The content of the image.
     * @since 1.0.0
     * @author YoungJin Sohn
     */
    data class ImageObjectDto(
        @SerializedName("content")
        val content: String?
    )

    /**
     * UploadImageRequestDto
     *
     * This data class provides the necessary DTO for the image upload request.
     *
     * @property userId The user ID of the image uploader.
     * @property content The content of the image.
     * @since 1.0.0
     * @author YoungJin Sohn
     */
    data class UploadImageRequestDto(
        @SerializedName("user_id")
        val userId: String?,

        @SerializedName("content")
        val content: String?
    )

    /**
     * UploadImageResponseDto
     *
     * This data class provides the necessary DTO for the image upload response.
     *
     * @property contentId The content ID of the uploaded image.
     * @since 1.0.0
     * @author YoungJin Sohn
     */
    data class UploadImageResponseDto(
        @SerializedName("content_id")
        val contentId: String?
    )

    /**
     * DownloadImageListResponseDto
     *
     * This data class provides the necessary DTO for the image list download response.
     *
     * @property imageList The list of images.
     * @since 1.0.0
     * @author YoungJin Sohn
     */
    data class DownloadImageListResponseDto(
        @SerializedName("images")
        val imageList: List<ImageObjectDto>?
    )
}
