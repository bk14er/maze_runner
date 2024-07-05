

const val LEAP = "Leap"
const val REGULAR = "Regular"

fun main() {
    val year = readln()!!.toInt()

    print(isLeapYear(year))

}

fun isLeapYear(year: Int): String {
    if(year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
        return LEAP
    return REGULAR
}
