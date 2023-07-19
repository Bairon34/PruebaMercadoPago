package com.example.pruebamercadopago.module.search.viewModel
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pruebamercadopago.model.ProductModel
import com.example.pruebamercadopago.model.ResultsModel
import com.example.pruebamercadopago.module.search.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val getProductSearch: SearchRepository): ViewModel() {
    val _product = MutableLiveData<List<ResultsModel>>()
    val product: LiveData<List<ResultsModel>>
        get() = _product

    val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean>
        get() = _loading

    val _result = MutableLiveData<Boolean>(false)
    val result: LiveData<Boolean>
        get() = _result


    fun getProductsService(productSeach: String) {
        _loading.value = true
       val response = getProductSearch.getProducts(productSeach)
        response.enqueue(object : retrofit2.Callback<ProductModel> {
            override fun onResponse(call: Call<ProductModel>, response: Response<ProductModel>) {
                if(response.body()?.results?.isNotEmpty() == true){
                    _product.postValue(response.body()?.results)
                    _result.value= false
                }else{
                    _result.value=true
                }
                _loading.value= false
            }
            override fun onFailure(call: Call<ProductModel>, t: Throwable) {
                Log.e("error", "message error " +t.message )
                _loading.value= false
                _result.value=true
            }
        })


    }
}