package com.sunilsahoo.assignment.myapplication.datasource

import com.sunilsahoo.assignment.myapplication.datasource.model.BaseStateResponse
import com.sunilsahoo.assignment.myapplication.features.todo.model.Todo
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NetworkDataSource {

    fun getTodo1(schedulerProvider: ISchedulerProvider): Single<BaseStateResponse<List<Todo>>> {
        return getApi().getTodos()
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.mainThread())
            .map { BaseStateResponse(it) }
    }

    fun getTodo(): Single<BaseStateResponse<List<Todo>>> {
        return getApi().getTodos()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { BaseStateResponse(it) }
    }

    fun isValid(input:String?): Boolean {
        return input.isNullOrEmpty().not()
    }

    private fun getApi(): NetworkApi {
        return NetworkApi.create()
    }
}
