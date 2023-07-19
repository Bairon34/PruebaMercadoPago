package com.example.pruebamercadopago.module.search.view.adapter

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pruebamercadopago.model.ResultsModel
import com.example.pruebamercadopago.databinding.AdapterViewProductBinding
import com.example.pruebamercadopago.module.detail.view.DetailProductActivity
import com.example.pruebamercadopago.utils.ESTATE_PRODUCT
import com.example.pruebamercadopago.utils.IMG_PRODUCT
import com.example.pruebamercadopago.utils.NAME_PRODUCT
import com.example.pruebamercadopago.utils.PAY_PRODUCT
import com.example.pruebamercadopago.utils.PHOTO_PRODUCT
import com.example.pruebamercadopago.utils.PRICE_PRODUCT

class ProductViewHolder(view: View):RecyclerView.ViewHolder(view) {
    var binding = AdapterViewProductBinding.bind(view)

    fun render(item: ResultsModel, context: Context) {

        binding.txtNameProduct.text= item.title
        binding.txtDeleveryProduct.text= item.condition
        binding.txtPriceProduct.text= item.price.toString()
        binding.txtStockProduct.text = item.stop_time

        Glide.with(context)
            .load(IMG_PRODUCT)
            .into( binding.imgProduct)

        binding.adapter.setOnClickListener {
            val myIntent =Intent(context, DetailProductActivity::class.java)
            myIntent.putExtra(NAME_PRODUCT, item.title)
            myIntent.putExtra(PRICE_PRODUCT, item.price.toString())
            myIntent.putExtra(PHOTO_PRODUCT, item.thumbnail)
            myIntent.putExtra(ESTATE_PRODUCT, item.condition)
            myIntent.putExtra(PAY_PRODUCT, item.accepts_mercadopago)
            context.startActivity(myIntent)
        }

    }
}