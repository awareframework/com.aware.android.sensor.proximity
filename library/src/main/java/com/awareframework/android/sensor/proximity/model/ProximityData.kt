package com.awareframework.android.sensor.proximity.model

import com.awareframework.android.core.model.AwareObject
import com.google.gson.Gson

/**
 * Contains the raw sensor data.
 *
 * @author  sercant
 * @date 27/07/2018
 */
data class ProximityData(
        var proximity: Float = 0f,
        var eventTimestamp: Long = 0L,
        var accuracy: Int = 0
) : AwareObject(jsonVersion = 1) {

    companion object {
        const val TABLE_NAME = "proximityData"
    }

    override fun toString(): String = Gson().toJson(this)
}