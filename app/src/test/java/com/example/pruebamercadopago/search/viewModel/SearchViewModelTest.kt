package com.example.pruebamercadopago.search.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.pruebamercadopago.model.ProductModel
import com.example.pruebamercadopago.model.ResultsModel
import com.example.pruebamercadopago.module.search.repository.SearchRepository
import com.example.pruebamercadopago.module.search.viewModel.SearchViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.lang.Exception


class SearchViewModelTest{

    @get:Rule
    var rule = InstantTaskExecutorRule()

    lateinit var viewModel: SearchViewModel

    @RelaxedMockK
    private lateinit var searchRepository: SearchRepository


    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        viewModel = SearchViewModel(searchRepository)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun stup(){
        Dispatchers.resetMain()
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

    @Test
    fun `test in function getProductsService when list resut is null`() = runTest {
            val results: List<ResultsModel> = emptyList()

            val data = ProductModel(
                "",
                "",
                "",
                results
            )
            val produtSearchs = "product"

            coEvery {
                searchRepository.getProductFlow(produtSearchs)
            } returns  flow {
                emit(data)
                throw Exception("Test cash")
            }
            viewModel.getProductListFlow(produtSearchs)

        assertEquals(0, data.results.size)
        assertEquals(false,viewModel.loading.value)

    }

    @Test
    fun `test in function getProductsService when list has data`() = runTest {
        val results: List<ResultsModel> = listOf()

        val data = ProductModel(
            "",
            "",
            "",
            results
        )
        val produtSearchs = "product"

        coEvery {
            searchRepository.getProductFlow(produtSearchs)
        } returns  flow {
            emit(data)
            throw Exception("Test cash")
        }
        viewModel.getProductListFlow(produtSearchs)

//        assertEquals("123",viewModel.product.value?.get(0)?.catalog_product_id)

    }

}