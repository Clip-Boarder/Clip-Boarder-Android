package com.clipboarder.clipboarder.data.remote.api

import com.clipboarder.clipboarder.data.remote.dto.ApiResponseDto
import com.clipboarder.clipboarder.data.remote.dto.ContentDto
import com.clipboarder.clipboarder.data.remote.dto.ImageDto
import com.clipboarder.clipboarder.data.remote.dto.SignInDto
import com.clipboarder.clipboarder.data.remote.dto.TextDto
import com.clipboarder.clipboarder.data.remote.dto.TextDto.UploadTextResponseDto
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

/**
 * ApiService
 *
 * This interface provides the necessary API endpoints for the application.
 *
 * @since 1.0.0
 * @author YoungJin Sohn
 */
interface ApiService {
    @POST("api/login")
    suspend fun signIn(@Body loginRequest: SignInDto.SignInRequestDto): Response<ApiResponseDto<SignInDto.SignInResponseDto>>

    @POST("api/text")
    suspend fun uploadText(@Body uploadTextRequest: TextDto.UploadTextRequestDto): Response<ApiResponseDto<UploadTextResponseDto>>

    @GET("api/text")
    suspend fun downloadTextList(): Response<ApiResponseDto<TextDto.DownloadTextListResponseDto>>

    @Multipart
    @POST("api/image")
    suspend fun uploadImage(@Part image: MultipartBody.Part): Response<ApiResponseDto<ImageDto.UploadImageResponseDto>>

    @GET("api/image")
    suspend fun downloadImageList(): Response<ApiResponseDto<ImageDto.DownloadImageListResponseDto>>

    @GET("api/content")
    suspend fun downloadContentList(): Response<ApiResponseDto<ContentDto.DownloadContentListResponseDto>>
}