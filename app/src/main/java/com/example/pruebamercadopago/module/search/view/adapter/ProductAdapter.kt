package com.example.pruebamercadopago.module.search.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebamercadopago.R
import com.example.pruebamercadopago.model.ResultsModel

class ProductAdapter(
    private val resultList: List<ResultsModel>,
    private  val context: Context
): RecyclerView.Adapter<ProductViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
       val layoutInflater= LayoutInflater.from(parent.context)
        return ProductViewHolder(
            layoutInflater.inflate(R.layout.adapter_view_product, parent,false)
        )
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
     val item =  resultList[position]
        if(item != null){
            holder.render(item,context)
        }
    }

    override fun getItemCount(): Int = resultList.size

}

