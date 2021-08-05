package com.sunilsahoo.assignment.myapplication.datasource

import com.sunilsahoo.assignment.myapplication.datasource.model.BaseStateResponse
import com.sunilsahoo.assignment.myapplication.features.todo.model.Todo
import com.sunilsahoo.assignment.myapplication.datasource.model.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class TodoDataSource(private val dispatcher: CoroutineDispatcher, private val networkRepo: NetworkRepo) {

    suspend fun execute(): BaseStateResponse<Todo> {
        return try {
            withContext(dispatcher) {
                networkRepo.getDetail().let {
                    BaseStateResponse(it)
                }
            }
        } catch (e: Exception) {
            BaseStateResponse(e)
        }
    }
}