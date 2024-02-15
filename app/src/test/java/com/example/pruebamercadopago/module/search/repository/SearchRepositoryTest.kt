package com.example.pruebamercadopago.module.search.repository

import com.example.pruebamercadopago.data.network.ProductApiClient
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class SearchRepositoryTest {

    lateinit var searchRepository: SearchRepository

    @RelaxedMockK
    lateinit var productApiClient : ProductApiClient

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        searchRepository = SearchRepository(productApiClient)

    }
    @After
    fun after(){
        Dispatchers.resetMain()
    }

    @Test
    fun getProducts() = runTest{
        searchRepository.getProducts("hjhjhj")
    }

    @Test
    fun getProductFlow(){
        searchRepository.getProductFlow("hjhjhj")
    }
}