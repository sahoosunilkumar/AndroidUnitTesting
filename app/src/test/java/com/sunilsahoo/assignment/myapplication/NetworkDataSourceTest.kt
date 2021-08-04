package com.sunilsahoo.assignment.myapplication

import com.sunilsahoo.assignment.myapplication.datasource.ISchedulerProvider
import com.sunilsahoo.assignment.myapplication.datasource.NetworkApi
import com.sunilsahoo.assignment.myapplication.datasource.NetworkDataSource
import com.sunilsahoo.assignment.myapplication.features.todo.model.Todo
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import java.lang.Exception

class NetworkDataSourceTest {
    @Rule @JvmField
    var schedulerRule = RxImmediateSchedulerRule()

    private val datasource = NetworkDataSource()
    @Test
    fun testGetTodos() {
        mockkObject(NetworkApi)
        val api :NetworkApi = mockk()
        every { NetworkApi.create() } returns api
        val todo = Todo()
        todo.userId=1
        val response = listOf(todo)
        every { api.getTodos() } returns Single.just(response)

        var result = datasource.getTodo().test()

        result.assertComplete()
        result.assertNoErrors()
        assertEquals(response, result.values().first().currentData)
        assertEquals(1, result.values().first().currentData!!.first().userId)
        result.dispose()

        every { api.getTodos() } returns Single.error(Exception())
        result = datasource.getTodo().test()
        result.assertError(Exception::class.java)
        result.dispose()

    }


    @Test
    fun testGetTodo1() {
        mockkObject(NetworkApi)
        val api :NetworkApi = mockk()
        val schedulerProvider: ISchedulerProvider = mockk()
        every { schedulerProvider.io() } returns Schedulers.trampoline()
        every { schedulerProvider.mainThread() } returns Schedulers.trampoline()
        every { NetworkApi.create() } returns api
        val todo = Todo()
        todo.userId=1
        val response = listOf(todo)
        every { api.getTodos() } returns Single.just(response)

        var result = datasource.getTodo1(schedulerProvider).test()

        result.assertComplete()
        result.assertNoErrors()
        result.dispose()
        assertEquals(response, result.values().first().currentData)
        assertEquals(1, result.values().first().currentData!!.first().userId)

        every { api.getTodos() } returns Single.error(Exception())
        result = datasource.getTodo1(schedulerProvider).test()
        result.assertError(Exception::class.java)
        result.dispose()

    }



}