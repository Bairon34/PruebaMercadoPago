package com.example.pruebamercadopago.module.search.repository
import android.util.Log
import com.example.pruebamercadopago.data.network.ProductApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val api: ProductApiClient
) {
    fun getProducts (wordSearchs:String)=  api.getProductSearchs(wordSearchs)

    fun getProductFlow(wordSearchs:String) = flow{
        val product = api.getProductSearchs(wordSearchs).execute().body()
        emit(product)
    }.flowOn(Dispatchers.IO)
}