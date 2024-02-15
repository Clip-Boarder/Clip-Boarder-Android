package com.clipboarder.clipboarder.data.remote.api

import com.clipboarder.clipboarder.data.remote.dto.ApiResponseDto
import com.clipboarder.clipboarder.data.remote.dto.ImageDto
import com.clipboarder.clipboarder.data.remote.dto.SignInDto
import com.clipboarder.clipboarder.data.remote.dto.SignUpDto
import com.clipboarder.clipboarder.data.remote.dto.TextDto
import com.clipboarder.clipboarder.data.remote.dto.TextDto.UploadTextResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * ApiService
 *
 * This interface provides the necessary API endpoints for the application.
 *
 * @since 1.0.0
 * @author YoungJin Sohn
 */
interface ApiService {
    @POST("/user")
    suspend fun signUp(@Body signUpRequestDto: SignUpDto.SignUpRequestDto): Response<ApiResponseDto<SignUpDto.SignUpResponseDto>>

    @POST("/login")
    suspend fun signIn(@Body loginRequest: SignInDto.SignInRequestDto): Response<ApiResponseDto<SignInDto.SignInResponseDto>>

    @POST("/text")
    suspend fun uploadText(@Body uploadTextRequest: TextDto.UploadTextRequestDto): Response<ApiResponseDto<UploadTextResponseDto>>

    @GET("/text")
    suspend fun downloadTextList(@Query("user_id") userId: String): Response<ApiResponseDto<TextDto.DownloadTextListResponseDto>>

    @POST("/image")
    suspend fun uploadImage(@Body uploadImageRequestDto: ImageDto.UploadImageRequestDto): Response<ApiResponseDto<ImageDto.UploadImageResponseDto>>

    @GET("/image")
    suspend fun downloadImageList(@Query("user_id") userId: String): Response<ApiResponseDto<ImageDto.DownloadImageListResponseDto>>
}