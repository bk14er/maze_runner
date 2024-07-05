fun main() {
    val n = readln().toInt()
    val numbers = IntArray(n)

    fillArray(n, numbers)

    val m = readln().toInt()
    var mTimes = 0

    for (i in 0 until n) {
        if (numbers[i] == m) {
            mTimes++
        }
    }

    println(mTimes)
}

private fun fillArray(n: Int, numbers: IntArray) {
    for (i in 0 until n) {
        numbers[i] = readln().toInt()
    }
}