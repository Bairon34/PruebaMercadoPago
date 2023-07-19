package com.example.pruebamercadopago.module.search.repository
import com.example.pruebamercadopago.data.network.ProductApiClient

import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val api: ProductApiClient
) {
    fun getProducts (wordSearchs:String)=  api.getProductSearchs(wordSearchs)
}