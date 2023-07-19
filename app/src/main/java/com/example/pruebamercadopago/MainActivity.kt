package com.example.pruebamercadopago

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pruebamercadopago.databinding.ActivityMainBinding
import com.example.pruebamercadopago.module.search.view.ListProductActivity
import com.example.pruebamercadopago.utils.PRODUC_SEARCHS
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        buttonSearchs()
    }

    private fun buttonSearchs() {
        binding.fab.setOnClickListener { view ->
          if(!binding.txtSearch.text.toString().trim().isNullOrEmpty()){
              val myIntent = Intent(this, ListProductActivity::class.java)
              myIntent.putExtra(PRODUC_SEARCHS, binding.txtSearch.text.toString().trim())
              this.startActivity(myIntent)
              binding.txtSearch.setText("")

          }else{
              Toast.makeText(this,"Campo vacio",Toast.LENGTH_LONG).show()
          }
        }
    }
}