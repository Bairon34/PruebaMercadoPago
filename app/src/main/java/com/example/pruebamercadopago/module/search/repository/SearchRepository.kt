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
        try{
            val response =  api.getProductSearchs(wordSearchs).execute()
            if(response.isSuccessful){
                val product = response.body()
                emit(product)
            }else{
                Log.e("error", "product no found" )
            }
        }catch (e : Exception){
            Log.e("error", e.message.toString())
        }
    }.flowOn(Dispatchers.IO)
}