package com.example.pruebamercadopago.module.search.viewModel
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebamercadopago.model.ProductModel
import com.example.pruebamercadopago.model.ResultsModel
import com.example.pruebamercadopago.module.search.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val getProductSearch: SearchRepository): ViewModel() {
    val _product = MutableLiveData<List<ResultsModel>?>()
    val product: MutableLiveData<List<ResultsModel>?>
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

    fun getProductListFlow(productSearch: String){
        viewModelScope.launch {
            getProductSearch.getProductFlow(productSearch)
                .onStart {
                    _loading.value= true
                    _result.value = false
                }
                .onCompletion {
                    _loading.value= false
                }
                .catch {
                    _result.value=true
                    Log.e("error", it.message.toString() )
                }
                .collect{
                    val resul = it?.results
                    if(!resul.isNullOrEmpty()){
                        _product.value = resul
                    }else{
                        _result.value = true
                    }
                }
        }
    }
}