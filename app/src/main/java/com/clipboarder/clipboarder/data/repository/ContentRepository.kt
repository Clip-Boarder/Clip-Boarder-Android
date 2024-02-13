package com.clipboarder.clipboarder.data.repository

import com.clipboarder.clipboarder.data.remote.api.ApiService
import com.clipboarder.clipboarder.data.remote.dto.ApiResponseDto
import com.clipboarder.clipboarder.data.remote.dto.DownloadTextListResponseDto
import com.clipboarder.clipboarder.data.remote.dto.UploadTextRequestDto
import com.clipboarder.clipboarder.data.remote.dto.UploadTextResponseDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * ContentRepository
 *
 * This class provides the necessary repository for the content data.
 *
 * @since 1.0.0
 * @author YoungJin Sohn
 */
class ContentRepository @Inject constructor(private val apiService: ApiService) {
    /**
     * copyTextToClipboarder
     *
     * This function copies the text to the clipboarder.
     *
     * @param userId The user ID of the text uploader.
     * @param text The content of the text.
     * @return The response of the text upload.
     */
    fun copyTextToClipboarder(
        userId: String,
        text: String
    ): Flow<ApiResponseDto<UploadTextResponseDto>> = flow {
        val uploadTextRequestDto = UploadTextRequestDto(userId, text)
        val response = apiService.uploadText(uploadTextRequestDto)
        if (response.isSuccessful && response.body() != null) {
            emit(response.body()!!)
        } else {
            throw Exception("Text Upload Failed: ${response.message()}")
        }
    }

    /**
     * getTextListFromClipboarder
     *
     * This function gets the list of texts from the clipboarder.
     *
     * @param userId The user ID of the text uploader.
     * @return The response of the text list download.
     */
    fun getTextListFromClipboarder(
        userId: String
    ): Flow<ApiResponseDto<DownloadTextListResponseDto>> = flow {
        val response = apiService.downloadTextList(userId)
        if (response.isSuccessful && response.body() != null) {
            emit(response.body()!!)
        } else {
            throw Exception("Text List Download Failed: ${response.message()}")
        }
    }

    /**
     * copyImageToClipboarder
     *
     * This function copies the image to the clipboarder.
     *
     * @param userId The user ID of the image uploader.
     * @param image The content of the image.
     * @return The response of the image upload.
     */
    fun copyImageToClipboarder(
        userId: String,
        image: String
    ): Flow<ApiResponseDto<UploadTextResponseDto>> = flow {
        val uploadTextRequestDto = UploadTextRequestDto(userId, image)
        val response = apiService.uploadText(uploadTextRequestDto)
        if (response.isSuccessful && response.body() != null) {
            emit(response.body()!!)
        } else {
            throw Exception("Image Upload Failed: ${response.message()}")
        }
    }

    /**
     * getImageListFromClipboarder
     *
     * This function gets the list of images from the clipboarder.
     *
     * @param userId The user ID of the image uploader.
     * @return The response of the image list download.
     */
    fun getImageListFromClipboarder(
        userId: String
    ): Flow<ApiResponseDto<DownloadTextListResponseDto>> = flow {
        val response = apiService.downloadTextList(userId)
        if (response.isSuccessful && response.body() != null) {
            emit(response.body()!!)
        } else {
            throw Exception("Image List Download Failed: ${response.message()}")
        }
    }
}