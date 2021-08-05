package com.sunilsahoo.assignment.myapplication.datasource.model

import androidx.annotation.IntDef

/**
 * Defines state of a response object
 */
object State {
    const val INIT = 0
    const val IN_PROGRESS = 1
    const val SUCCESS = 2
    const val ERROR = 3
    const val COMPLETE = 4
}

@Retention(AnnotationRetention.SOURCE)
@IntDef(State.INIT, State.IN_PROGRESS, State.SUCCESS, State.ERROR, State.COMPLETE)
annotation class IState
