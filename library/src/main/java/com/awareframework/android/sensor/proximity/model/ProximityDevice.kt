package com.awareframework.android.sensor.proximity.model

import com.awareframework.android.core.model.AwareObject
import com.google.gson.Gson

/**
 * Holds the information of the proximity sensor of the device
 *
 * @author  sercant
 * @date 27/07/2018
 */
data class ProximityDevice(
        var maxRange: Float = 0f,
        var minDelay: Float = 0f,
        var name: String = "",
        var power: Float = 0f, // in Ma
        var resolution: Float = 0f,
        var type: String = "",
        var vendor: String = "",
        var version: String = ""
): AwareObject(jsonVersion = 1) {

    companion object {
        const val TABLE_NAME = "proximityDevice"
    }

    override fun toString(): String = Gson().toJson(this)
}