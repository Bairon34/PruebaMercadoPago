package com.example.pruebamercadopago.data.network

import javax.inject.Inject

class ProductService @Inject constructor(
    private val api:ProductApiClient
) {
    suspend fun getProducts (wordSearchs:String)=  api.getProductSearchs(wordSearchs)
}


