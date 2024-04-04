package com.clipboarder.clipboarder.data.repository

import com.clipboarder.clipboarder.data.remote.api.ApiService
import com.clipboarder.clipboarder.data.remote.dto.ApiResponseDto
import com.clipboarder.clipboarder.data.remote.dto.ContentDto
import com.clipboarder.clipboarder.data.remote.dto.ImageDto
import com.clipboarder.clipboarder.data.remote.dto.TextDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
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
     * @param text The content of the text.
     * @return The response of the text upload.
     */
    fun copyTextToClipboarder(
        text: String
    ): Flow<ApiResponseDto<TextDto.UploadTextResponseDto>> = flow {
        val uploadTextRequestDto = TextDto.UploadTextRequestDto(text)
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
     * @return The response of the text list download.
     */
    fun getTextListFromClipboarder(
    ): Flow<ApiResponseDto<TextDto.DownloadTextListResponseDto>> = flow {
        val response = apiService.downloadTextList()
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
     * @param imageData The image data to upload.
     * @return The response of the image upload.
     */
    fun copyImageToClipboarder(
        imageData: MultipartBody.Part
    ): Flow<ApiResponseDto<ImageDto.UploadImageResponseDto>> = flow {
        val response = apiService.uploadImage(imageData)
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
     * @return The response of the image list download.
     */
    fun getImageListFromClipboarder(): Flow<ApiResponseDto<ImageDto.DownloadImageListResponseDto>> =
        flow {
            val response = apiService.downloadImageList()
            if (response.isSuccessful && response.body() != null) {
                emit(response.body()!!)
            } else {
                throw Exception("Image List Download Failed: ${response.message()}")
            }
        }

    /**
     * getContentListFromClipboarder
     *
     * This function gets the list of contents from the clipboarder.
     *
     * @param page The page number of the content list.
     * @return The response of the content list download.
     */
    fun getContentListFromClipboarder(page: Int): Flow<ApiResponseDto<ContentDto.DownloadContentListResponseDto>> =
        flow {
            val response = apiService.downloadContentList(page)
            if (response.isSuccessful && response.body() != null) {
                emit(response.body()!!)
            } else {
                throw Exception("Content List Download Failed: ${response.message()}")
            }
        }
}