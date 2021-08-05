package com.sunilsahoo.assignment.myapplication.datasource

import com.sunilsahoo.assignment.myapplication.features.todo.model.Todo

class NetworkRepo {

    fun getDetail(): Todo {
        return getApi().getTodoDetail().execute().body()!!
    }

    private fun getApi(): NetworkApi {
        return NetworkApi.create()
    }
}
