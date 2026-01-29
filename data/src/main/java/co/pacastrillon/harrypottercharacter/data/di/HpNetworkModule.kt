package co.pacastrillon.harrypottercharacter.data.di

import co.pacastrillon.harrypottercharacter.data.remote.HpApiJson
import co.pacastrillon.harrypottercharacter.data.remote.HpApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HpNetworkModule {

    private const val BASE_URL = "https://hp-api.onrender.com/"

    @Provides
    @Singleton
    @HpApiJson
    fun provideHpJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Provides
    @Singleton
    fun provideHpRetrofit(
        okHttpClient: OkHttpClient,
        @HpApiJson json: Json
    ): Retrofit {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }

    @Provides
    @Singleton
    fun provideHpApiService(retrofit: Retrofit): HpApiService =
        retrofit.create(HpApiService::class.java)
}