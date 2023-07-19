package com.example.pruebamercadopago.module.search.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pruebamercadopago.databinding.ActivityListProductBinding
import com.example.pruebamercadopago.module.search.view.adapter.ProductAdapter
import com.example.pruebamercadopago.module.search.viewModel.SearchViewModel
import com.example.pruebamercadopago.utils.PRODUC_SEARCHS
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListProductBinding
    private val productSearchs by lazy { intent.getStringExtra(PRODUC_SEARCHS) }
    private val searchViewModel: SearchViewModel by viewModels()

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        binding = ActivityListProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        observer()
        searchViewModel.getProductsService(productSearchs!!)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observer() {
        searchViewModel.loading.observe(this){
            binding.progressBar.isVisible= it
        }

        searchViewModel.result.observe(this){
            binding.txtResult.isVisible= it
        }

        searchViewModel.product.observe(this){
            if(it.isNotEmpty()){
                binding.rcvListProduct.layoutManager= LinearLayoutManager(this)
                binding.rcvListProduct.adapter= ProductAdapter(it,this)
                binding.rcvListProduct.adapter!!.notifyDataSetChanged()
            }
        }
    }


}