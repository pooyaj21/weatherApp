package di

import model.Location
import model.Pollution
import model.Weather

 object ModelProvider {
     fun provideWeatherModel() = Weather::class.java
    fun providePollutionModel() = Pollution::class.java
    fun provideLocationModel() = Location::class.java
    val string="fuck"
}