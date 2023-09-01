package com.sxiaozhi.debt.di

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.sxiaozhi.debt.BuildConfig
import com.sxiaozhi.debt.api.NetworkResponseAdapterFactory
import com.sxiaozhi.debt.api.interceptor.HeaderInterceptor
import com.sxiaozhi.debt.api.typeadapter.TypeAdapters
import com.sxiaozhi.debt.data.typeconverters.BooleanAdapters
import com.sxiaozhi.debt.utils.Logger
import com.sxiaozhi.debt.utils.SharedPrefService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
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
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(BooleanAdapters())
            .add(TypeAdapters())
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideMoshiConverterFactory(moshi: Moshi): MoshiConverterFactory {
        return MoshiConverterFactory.create(moshi)
    }

    @Provides
    @Singleton
    fun provideNetworkResponseAdapterFactory(): NetworkResponseAdapterFactory {
        return NetworkResponseAdapterFactory()
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
        factory: MoshiConverterFactory,
        networkResponseAdapterFactory: NetworkResponseAdapterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(factory)
            .addCallAdapterFactory(networkResponseAdapterFactory)
            .client(client)
            .build()
    }

    private companion object {
        private const val TIMEOUT_SECONDS = 60L
    }
}
