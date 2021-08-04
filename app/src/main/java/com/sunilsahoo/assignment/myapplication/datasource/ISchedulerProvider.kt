package com.sunilsahoo.assignment.myapplication.datasource

import io.reactivex.Scheduler

/**
 * Provides schedulers
 */
interface ISchedulerProvider {
    fun io(): Scheduler
    fun computation(): Scheduler
    fun mainThread(): Scheduler
}
