// Check if numbers are in ascending order
fun main() {
    val n = readln().toInt()
    var statement = "YES"
    var prev = 0

    for (i in 1..n) {
        val next = readln().toInt()

        if (next < prev) {
            statement = "NO"
            break
        }

        prev = next
    }

    println(statement)


}