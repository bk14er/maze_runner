const val MAX_POPULATION = 50_000_000
const val WHEN_NEGATIVE = 0


class City(val name: String) {
    var population: Int = MAX_POPULATION
        set(value) {
            field = if (value > MAX_POPULATION) MAX_POPULATION else
                if (value < 0) WHEN_NEGATIVE else value
        }
}