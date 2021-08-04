package com.sunilsahoo.assignment.myapplication.datasource

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Provides schedulers
 */
class SchedulerProvider : ISchedulerProvider {
    override fun computation() = Schedulers.computation()
    override fun mainThread() = AndroidSchedulers.mainThread()
    override fun io() = Schedulers.io()
}
