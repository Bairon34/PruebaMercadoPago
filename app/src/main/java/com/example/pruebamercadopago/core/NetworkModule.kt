package com.example.pruebamercadopago.core

import com.example.pruebamercadopago.data.network.ProductApiClient
import com.example.pruebamercadopago.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Singleton
    @Provides
    fun provideProductApiClient(retrofit: Retrofit): ProductApiClient {
        return retrofit.create(ProductApiClient::class.java)
    }


}