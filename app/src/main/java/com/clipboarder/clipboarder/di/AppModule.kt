package com.clipboarder.clipboarder.di

import com.clipboarder.clipboarder.BuildConfig
import com.clipboarder.clipboarder.data.remote.api.ApiService
import com.clipboarder.clipboarder.data.repository.ContentRepository
import com.clipboarder.clipboarder.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * AppModule
 *
 * This module provides the necessary dependencies for the application.
 *
 * @since 1.0.0
 * @author YoungJin Sohn
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(3, TimeUnit.SECONDS)
            .readTimeout(3, TimeUnit.SECONDS)
            .writeTimeout(3, TimeUnit.SECONDS)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl(BuildConfig.clipboarderBaseUrl).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideUserRepository(apiService: ApiService): UserRepository =
        UserRepository(apiService)

    @Provides
    @Singleton
    fun provideContentRepository(apiService: ApiService): ContentRepository =
        ContentRepository(apiService)
}