fun main() {
    val value = readln().toDouble()

    // print it with a thousand separator and exactly 2 decimal digits
    val doubleString = String.format("%,.2f",value)

    println(doubleString )
}