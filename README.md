# AWARE Proximity

[![jitpack-badge](https://jitpack.io/v/awareframework/com.aware.android.sensor.proximity.svg)](https://jitpack.io/#awareframework/com.aware.android.sensor.proximity)

The proximity sensor measures the distance to an object in front of the mobile device. Depending on the hardware, it can be in centimeters or binary.

## Public functions

### ProximitySensor

+ `start(context: Context, config: ProximitySensor.Config?)`: Starts the proximity sensor with the optional configuration.
+ `stop(context: Context)`: Stops the service.
+ `currentInterval`: Data collection rate per second. (e.g. 5 samples per second)

### ProximitySensor.Config

Class to hold the configuration of the sensor.

#### Fields

+ `sensorObserver: ProximitySensor.Observer`: Callback for live data updates.
+ `interval: Int`: Data samples to collect per second. (default = 5)
+ `period: Float`: Period to save data in minutes. (default = 1)
+ `threshold: Double`: If set, do not record consecutive points if change in value is less than the set value.
+ `enabled: Boolean` Sensor is enabled or not. (default = false)
+ `debug: Boolean` enable/disable logging to `Logcat`. (default = false)
+ `label: String` Label for the data. (default = "")
+ `deviceId: String` Id of the device that will be associated with the events and the sensor. (default = "")
+ `dbEncryptionKey` Encryption key for the database. (default =String? = null)
+ `dbType: Engine` Which db engine to use for saving data. (default = `Engine.DatabaseType.NONE`)
+ `dbPath: String` Path of the database. (default = "aware_proximity")
+ `dbHost: String` Host for syncing the database. (Defult = `null`)

## Broadcasts

### Fired Broadcasts

+ `ProximitySensor.ACTION_AWARE_PROXIMITY` fired when proximity saved data to db after the period ends.

### Received Broadcasts

+ `ProximitySensor.ACTION_AWARE_PROXIMITY_START`: received broadcast to start the sensor.
+ `ProximitySensor.ACTION_AWARE_PROXIMITY_STOP`: received broadcast to stop the sensor.
+ `ProximitySensor.ACTION_AWARE_PROXIMITY_SYNC`: received broadcast to send sync attempt to the host.
+ `ProximitySensor.ACTION_AWARE_PROXIMITY_SET_LABEL`: received broadcast to set the data label. Label is expected in the `ProximitySensor.EXTRA_LABEL` field of the intent extras.

## Data Representations

### Proximity Sensor

Contains the hardware sensor capabilities in the mobile device.

| Field      | Type   | Description                                                     |
| ---------- | ------ | --------------------------------------------------------------- |
| maxRange   | Float  | Maximum sensor value possible                                   |
| minDelay   | Float  | Minimum sampling delay in microseconds                          |
| name       | String | Sensor’s name                                                  |
| power      | Float  | Sensor’s power drain in mA                                     |
| resolution | Float  | Sensor’s resolution in sensor’s units                         |
| type       | String | Sensor’s type                                                  |
| vendor     | String | Sensor’s vendor                                                |
| version    | String | Sensor’s version                                               |
| deviceId   | String | AWARE device UUID                                               |
| label      | String | Customizable label. Useful for data calibration or traceability |
| timestamp  | Long   | unixtime milliseconds since 1970                                |
| timezone   | Int    | [Raw timezone offset][1] of the device                          |
| os         | String | Operating system of the device (ex. android)                    |

### Proximity Data

Contains the raw sensor data.

| Field     | Type   | Description                                                                                          |
| --------- | ------ | ---------------------------------------------------------------------------------------------------- |
| proximity | Float  | the distance to an object in front of the mobile device or binary presence (manufacturer dependent). |
| accuracy  | Int    | Sensor’s accuracy level (see [SensorManager][2])                                                    |
| label     | String | Customizable label. Useful for data calibration or traceability                                      |
| deviceId  | String | AWARE device UUID                                                                                    |
| label     | String | Customizable label. Useful for data calibration or traceability                                      |
| timestamp | Long   | unixtime milliseconds since 1970                                                                     |
| timezone  | Int    | [Raw timezone offset][1] of the device                                                               |
| os        | String | Operating system of the device (ex. android)                                                         |

## Example usage

```kotlin
// To start the service.
ProximitySensor.start(appContext, ProximitySensor.Config().apply {
    sensorObserver = object : ProximitySensor.Observer {
        override fun onDataChanged(data: ProximityData) {
            // your code here...
        }
    }
    dbType = Engine.DatabaseType.ROOM
    debug = true
    // more configuration...
})

// To stop the service
ProximitySensor.stop(appContext)
```

## License

Copyright (c) 2018 AWARE Mobile Context Instrumentation Middleware/Framework (http://www.awareframework.com)

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.

[1]: https://developer.android.com/reference/java/util/TimeZone#getRawOffset()
[2]: http://developer.android.com/reference/android/hardware/SensorManager.html