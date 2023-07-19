package com.example.pruebamercadopago.model

import com.google.gson.annotations.SerializedName

data class ProductModel(

    @SerializedName("site_id")
    val site_id: String,
    @SerializedName("country_default_time_zone")
    val country_default_time_zone: String,
    @SerializedName("query")
    val query: String,
    @SerializedName("results")
    val results: List<ResultsModel>
)

