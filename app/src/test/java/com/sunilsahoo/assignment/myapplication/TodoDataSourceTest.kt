package com.asda.android.authorization.repo.usecase

import com.sunilsahoo.assignment.myapplication.CoroutineTestRule
import com.sunilsahoo.assignment.myapplication.datasource.NetworkRepo
import com.sunilsahoo.assignment.myapplication.datasource.TodoDataSource
import com.sunilsahoo.assignment.myapplication.datasource.model.BaseStateResponse
import com.sunilsahoo.assignment.myapplication.datasource.model.data
import com.sunilsahoo.assignment.myapplication.features.todo.model.Todo
import io.mockk.*
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class TodoDataSourceTest {

    private lateinit var datasource: TodoDataSource
    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @MockK
    lateinit var networkRepo: NetworkRepo

    @Before
    fun setUp() {
        clearAllMocks()
        unmockkAll()
        MockKAnnotations.init(this)
        datasource = spyk(TodoDataSource(coroutinesTestRule.testDispatcher, networkRepo))
    }

    @Test
    fun testExecute() {
        val response = Todo()
        every { networkRepo.getDetail() } returns response
        coroutinesTestRule.testDispatcher.runBlockingTest {
            val result = datasource.execute()
            assertEquals(response, result.currentData)
            assertNotNull(result)
        }
    }
}
