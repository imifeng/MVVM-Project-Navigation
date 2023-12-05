package com.sxiaozhi.fragment.di

import android.content.Context
import com.google.gson.GsonBuilder
import com.sxiaozhi.fragment.BuildConfig
import com.sxiaozhi.fragment.api.HomeApi
import com.sxiaozhi.fragment.api.UserApi
import com.sxiaozhi.fragment.api.interceptor.HeaderInterceptor
import com.sxiaozhi.fragment.utils.Logger
import com.sxiaozhi.fragment.utils.SharedPrefService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideSharedPrefService(@ApplicationContext context: Context): SharedPrefService {
        return SharedPrefService(context)
    }

    @Provides
    fun providesHeaderInterceptor(sharedPrefService: SharedPrefService): HeaderInterceptor {
        return HeaderInterceptor(sharedPrefService)
    }

    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor {
            Logger.d("OkHttp", it)
        }.apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
    }

    @Provides
    fun provideConverterFactory(): Converter.Factory {
        val gsonBuilder = GsonBuilder()
        return GsonConverterFactory.create(gsonBuilder.create())
    }

    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        headerInterceptor: HeaderInterceptor
    ): OkHttpClient {
        val okHttpClient = OkHttpClient().newBuilder()
        okHttpClient.callTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        okHttpClient.connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        okHttpClient.readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        okHttpClient.writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        okHttpClient.addInterceptor(httpLoggingInterceptor)
        okHttpClient.addInterceptor(headerInterceptor)
        okHttpClient.build()
        return okHttpClient.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient,
        converterFactory: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(converterFactory)
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideHomeApi(retrofit: Retrofit): HomeApi {
        return retrofit.create(HomeApi::class.java)
    }

    @Singleton
    @Provides
    fun provideUserApi(retrofit: Retrofit): UserApi {
        return retrofit.create(UserApi::class.java)
    }


    private companion object {
        private const val TIMEOUT_SECONDS = 60L
    }
}
