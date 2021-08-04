package com.sunilsahoo.assignment.myapplication.features.todo

import androidx.lifecycle.ViewModel
import com.sunilsahoo.assignment.myapplication.datasource.model.BaseStateResponse
import com.sunilsahoo.assignment.myapplication.datasource.NetworkDataSource
import com.sunilsahoo.assignment.myapplication.features.todo.model.Todo
import io.reactivex.Single

class MainViewModel(private val dataSource:NetworkDataSource) : ViewModel() {
    fun getTodo(): Single<BaseStateResponse<List<Todo>>> {
        return dataSource.getTodo()
    }

}