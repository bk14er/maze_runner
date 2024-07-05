fun main() {
    // put your code here
    val a = readln()!!.toInt()
    val b = readln()!!.toInt()

    if (a > b) println(sum(b, a)) else println(sum(a, b))
}

private fun sum(a: Int, b: Int): Int {
    var sum = 0

    for (i in a..b) {
        sum += i
    }

    return sum
}