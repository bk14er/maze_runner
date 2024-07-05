const val MIN_TEMP = -92
const val MAX_TEMP = 57


class City(val name: String) {
    private val defaultTemperature: Int by lazy {
        when (name) {
            "Dubai" -> 30
            "Hanoi" -> 20
            else -> 5
        }
    }

    var degrees: Int = 0
        set(value) {
            field = if (value > MAX_TEMP || value < MIN_TEMP) defaultTemperature else value
        }
}

fun main() {
    val first = readLine()!!.toInt()
    val second = readLine()!!.toInt()
    val third = readLine()!!.toInt()
    val firstCity = City("Dubai")
    firstCity.degrees = first
    val secondCity = City("Moscow")
    secondCity.degrees = second
    val thirdCity = City("Hanoi")
    thirdCity.degrees = third

    val coldestCities = listOf(firstCity, secondCity, thirdCity).sortedBy { it.degrees }


    val result =
        if (coldestCities[0].degrees == coldestCities[1].degrees) "neither" else coldestCities[0].name

    print(result)
}