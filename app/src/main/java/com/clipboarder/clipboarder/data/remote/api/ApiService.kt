package com.clipboarder.clipboarder.data.remote.api

import com.clipboarder.clipboarder.data.remote.dto.ApiResponseDto
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
    @POST("login")
    suspend fun signIn(@Body loginRequest: SignInDto.SignInRequestDto): Response<ApiResponseDto<SignInDto.SignInResponseDto>>

    @POST("text")
    suspend fun uploadText(@Body uploadTextRequest: TextDto.UploadTextRequestDto): Response<ApiResponseDto<UploadTextResponseDto>>

    @GET("text")
    suspend fun downloadTextList(): Response<ApiResponseDto<TextDto.DownloadTextListResponseDto>>

    @Multipart
    @POST("image")
    suspend fun uploadImage(@Part image: MultipartBody.Part): Response<ApiResponseDto<ImageDto.UploadImageResponseDto>>

    @GET("image")
    suspend fun downloadImageList(): Response<ApiResponseDto<ImageDto.DownloadImageListResponseDto>>
}