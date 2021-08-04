package com.sunilsahoo.assignment.myapplication

import androidx.annotation.VisibleForTesting
import com.sunilsahoo.assignment.myapplication.datasource.DataSourceConfig
import com.sunilsahoo.assignment.myapplication.datasource.NetworkApi
import com.sunilsahoo.assignment.myapplication.datasource.NetworkRepo
import com.sunilsahoo.assignment.myapplication.features.todo.model.Score

class Utils {
    /**
     * comma separated unique ids
     */
    @VisibleForTesting
    fun getUniqueIds(list: List<String?>?): String {
        return list?.filterNotNull()?.distinct()?.joinToString(",").orEmpty()
    }

    @VisibleForTesting
    fun getIds(list: List<String?>?): String {
        return list?.joinToString(",").orEmpty()
    }

    fun convertToString(allowDuplicates: String?, list: List<String?>?): String {
        //complexity 1
        return if (DataSourceConfig.checkBoolean(allowDuplicates)) {
            //complexity 2
            getUniqueIds(list)
        } else {
            //complexity 3
            getIds(list)
        }
    }
}
