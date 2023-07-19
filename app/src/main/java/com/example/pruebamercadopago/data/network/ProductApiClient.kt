package com.example.pruebamercadopago.data.network

import com.example.pruebamercadopago.model.ProductModel
import com.example.pruebamercadopago.utils.GET_PRODUCTS
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductApiClient {
    @GET(GET_PRODUCTS)
    fun getProductSearchs(@Query("q") productSearch: String): Call<ProductModel>
}