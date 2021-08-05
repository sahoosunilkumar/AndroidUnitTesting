package com.sunilsahoo.assignment.myapplication.features.todo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MediatorLiveData
import com.sunilsahoo.assignment.myapplication.datasource.NetworkDataSource
import com.sunilsahoo.assignment.myapplication.datasource.model.State
import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {
    lateinit var viewModel: MainViewModel
    @MockK
    lateinit var dataSource: NetworkDataSource

    @Before
    fun setUp(){
        MockKAnnotations.init(this)
        viewModel = MainViewModel(dataSource)
    }

    @get:Rule
    val executerRule = InstantTaskExecutorRule()

    @Test
    fun testExecute(){
        every { dataSource.isValid(any()) } returns true
        viewModel.execute("test")
        assertEquals(2, viewModel.response.value)

        every { dataSource.isValid(any()) } returns false
        viewModel.execute("test")
        assertEquals(3, viewModel.response.value)
    }


    @Test
    fun testExecute1(){
        viewModel = spyk(viewModel)
        val response : MediatorLiveData<Int> = mockk()
        every { viewModel.getLiveData() } returns response
        every { response.postValue(any()) } returns Unit

        every { dataSource.isValid(any()) } returns true
        viewModel.execute1("test")
        verify (exactly = 1){ response.postValue(2) }

        every { dataSource.isValid(any()) } returns false
        viewModel.execute1("test")
        verify (exactly = 1){ response.postValue(3) }
    }
}