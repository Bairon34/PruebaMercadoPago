package com.example.pruebamercadopago.module.detail.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.WindowCompat
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.pruebamercadopago.databinding.ActivityDetailProductBinding
import com.example.pruebamercadopago.utils.ESTATE_PRODUCT
import com.example.pruebamercadopago.utils.IMG_PRODUCT
import com.example.pruebamercadopago.utils.NAME_PRODUCT
import com.example.pruebamercadopago.utils.PAY_PRODUCT
import com.example.pruebamercadopago.utils.PHOTO_PRODUCT
import com.example.pruebamercadopago.utils.PRICE_PRODUCT

class DetailProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailProductBinding
    private val nameProduct by lazy { intent.getStringExtra(NAME_PRODUCT) }
    private val priceProduct by lazy { intent.getStringExtra(PRICE_PRODUCT) }
    private val photoProduct by lazy { intent.getStringExtra(PHOTO_PRODUCT) }
    private val estateProduct by lazy { intent.getStringExtra(ESTATE_PRODUCT) }
    private val payProduct by lazy { intent.getBooleanExtra(PAY_PRODUCT,false) }

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        viewData()
    }

    private fun viewData() {
        binding.txtNameProduct.text = nameProduct
        binding.txtPriceProduct.text = "Precio: $$priceProduct"
        binding.txtEstateProduct.text = "Eestado: $estateProduct"
        binding.txtOtherProduct.isVisible = payProduct


        Glide.with(this)
            .load(IMG_PRODUCT)
            .into( binding.imgProduct)
    }
}