package com.sunilsahoo.assignment.myapplication.datasource.model

import androidx.annotation.VisibleForTesting
import com.asda.android.base.interfaces.IResponse

/**
 *  response data with state
 *
 * @param <T>
</T> */
open class BaseStateResponse<T> : IResponse<T> {
    override fun getData(): T? {
        return this.currentData
    }

    override fun getError(): Throwable? {
        return this.currentError
    }

    @IState
    override fun getState(): Int {
        return this.currentState
    }

    var metaData: String? = null
    @VisibleForTesting
    var currentData: T? = null
    @VisibleForTesting
    var currentError: Throwable? = null
    @IState
    @VisibleForTesting
    var currentState: Int = State.INIT

    constructor(data: T) {
        this.currentState = State.SUCCESS
        this.currentData = data
    }

    constructor(error: Throwable) {
        this.currentState = State.ERROR
        this.currentError = error
    }

    constructor(@IState status: Int) {
        setState(status)
    }

    fun setState(@IState status: Int) {
        this.currentState = status
    }
}
