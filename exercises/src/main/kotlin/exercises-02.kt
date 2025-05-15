package dhbw

import java.util.Optional


fun main() {


}

sealed class Sensor(val description: String, val unitOfMeasure: String)

data class TEMPERATURE(val des: String, val unit: String) : Sensor(des, unit)
data class HUMIDITY(val des: String, val unit: String) : Sensor(des, unit)
data class AIRPRESSURE(val des: String, val unit: String) : Sensor(des, unit)
data class PRECIPITATION(val des: String, val unit: String) : Sensor(des, unit)

data class Measurement(val timestamp: Long, val value: Double, val sensor: Sensor)

data class WeatherStation(val id: Int, val location: String, val measurements: List<Measurement>)

fun WeatherStation.addMeasurement(measurement: Measurement): WeatherStation {
    return this.copy(measurements = this.measurements + measurement)
}

fun WeatherStation.getAverageValueBySensor(sensor: Sensor, start: Long, end: Long): Optional<Double> {
    val values = measurements
        .filter { it.sensor == sensor && it.timestamp in start..end }
        .map { it.value }

    return if (values.isNotEmpty()) {
        Optional.of(values.average())
    } else {
        Optional.empty()
    }
}

