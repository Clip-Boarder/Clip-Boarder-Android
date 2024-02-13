package com.clipboarder.clipboarder.data.remote.api

import com.clipboarder.clipboarder.data.remote.dto.ApiResponseDto
import com.clipboarder.clipboarder.data.remote.dto.DownloadImageListResponseDto
import com.clipboarder.clipboarder.data.remote.dto.DownloadTextListResponseDto
import com.clipboarder.clipboarder.data.remote.dto.SignInRequestDto
import com.clipboarder.clipboarder.data.remote.dto.SignInResponseDto
import com.clipboarder.clipboarder.data.remote.dto.SignUpRequestDto
import com.clipboarder.clipboarder.data.remote.dto.SignUpResponseDto
import com.clipboarder.clipboarder.data.remote.dto.UploadImageRequestDto
import com.clipboarder.clipboarder.data.remote.dto.UploadImageResponseDto
import com.clipboarder.clipboarder.data.remote.dto.UploadTextRequestDto
import com.clipboarder.clipboarder.data.remote.dto.UploadTextResponseDto
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
    suspend fun signUp(@Body signUpRequestDto: SignUpRequestDto): Response<ApiResponseDto<SignUpResponseDto>>

    @POST("/login")
    suspend fun signIn(@Body loginRequest: SignInRequestDto): Response<ApiResponseDto<SignInResponseDto>>

    @POST("/text")
    suspend fun uploadText(@Body uploadTextRequest: UploadTextRequestDto): Response<ApiResponseDto<UploadTextResponseDto>>

    @GET("/text")
    suspend fun downloadTextList(@Query("user_id") userId: String): Response<ApiResponseDto<DownloadTextListResponseDto>>

    @POST("/image")
    suspend fun uploadImage(@Body uploadImageRequestDto: UploadImageRequestDto): Response<ApiResponseDto<UploadImageResponseDto>>

    @GET("/image")
    suspend fun downloadImageList(@Query("user_id") userId: String): Response<ApiResponseDto<DownloadImageListResponseDto>>
}