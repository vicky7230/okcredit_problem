package com.vicky7230.okcredit_problem.di.module


import com.vicky7230.okcredit_problem.BuildConfig
import com.vicky7230.okcredit_problem.data.network.ApiService
import com.vicky7230.okcredit_problem.di.BaseUrl
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    internal fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        return if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }
        } else {
            httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    @Provides
    @Singleton
    internal fun provideDisableCacheInterceptor(): Interceptor {
        return Interceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .header("cache-control", "no-cache")
            val request = requestBuilder.build()
            chain.proceed(request)
        }
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        disableCacheInterceptor: Interceptor
    ): OkHttpClient {

        return OkHttpClient.Builder()
            .readTimeout(10 * 1000, TimeUnit.MILLISECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(disableCacheInterceptor)
            .build()
    }

    @Provides
    @Singleton
    internal fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    internal fun provideRxJava2CallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(
        @BaseUrl baseUrl: String,
        gsonConverterFactory: GsonConverterFactory,
        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    internal fun provideRecipeService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}