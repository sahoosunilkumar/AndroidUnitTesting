package com.asda.android.base.interfaces

/**
 * Type for response details
 *
 * @param <T> type
 **/
interface IResponse<T> {
    fun getData(): T?

    fun getError(): Throwable?

    fun getState(): Int
}
