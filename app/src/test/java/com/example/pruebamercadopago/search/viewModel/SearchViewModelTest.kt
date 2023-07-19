package com.example.pruebamercadopago.search.viewModel

import com.example.pruebamercadopago.module.search.repository.SearchRepository
import com.example.pruebamercadopago.module.search.viewModel.SearchViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class SearchViewModelTest{
    @RelaxedMockK
    private lateinit var searchRepository: SearchRepository

    lateinit var viewModel: SearchViewModel

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        viewModel = SearchViewModel(searchRepository)
    }

    @Test
    fun `when consume service is diferent to null`()= runBlocking {
        val PRODUT_SEARCH = "samsung"
        coEvery { searchRepository.getProducts(PRODUT_SEARCH) }
        val response = viewModel.product
        assert(response!=null)
    }

    @Test
    fun `when consume service repository diferent to null`()= runBlocking {
        val PRODUT_SEARCH = "samsung"
        val repository = coEvery { searchRepository.getProducts(PRODUT_SEARCH) }
        assert(repository!=null)
    }


    @Test
    fun `when product search is incorrect for service `()= runBlocking {
        val PRODUT_SEARCH = "dfdf"
        coEvery { searchRepository.getProducts(PRODUT_SEARCH) }
        val  result = viewModel.result
        assert(result.value==false)
    }

}