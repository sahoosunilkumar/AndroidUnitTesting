package com.sunilsahoo.assignment.myapplication

import android.util.Log
import com.sunilsahoo.assignment.myapplication.datasource.DataSourceConfig
import com.sunilsahoo.assignment.myapplication.datasource.NetworkApi
import com.sunilsahoo.assignment.myapplication.datasource.NetworkRepo
import com.sunilsahoo.assignment.myapplication.features.todo.model.Score
import io.mockk.*
import junit.framework.Assert.assertEquals
import org.junit.AfterClass
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

class UtilsTest {

    @Before
    fun setUp(){
        clearAllMocks()
        unmockkAll()
    }

    @Test
    fun testConvertToString() {
        val utils = spyk(Utils())
        mockkObject(DataSourceConfig)
        every { DataSourceConfig.checkBoolean(any()) } returns true
        utils.convertToString("y", listOf("1", "2", "2"))
        verify { utils.getUniqueIds(any()) }
        every { DataSourceConfig.checkBoolean(any()) } returns false
        utils.convertToString("y", listOf("1", "2", "2"))
        verify { utils.getIds(any()) }
    }

    @Test
    fun testGetIds() {
        val utils = Utils()
        val result = utils.getIds(listOf("1","2","2"))
        assertEquals("1,2,2", result)
    }

    @Test
    fun testGetUniqueIds() {
        val utils = Utils()
        val result = utils.getUniqueIds(listOf("1","2","2"))
        assertEquals("1,2", result)
    }

}