fun main() {
    val n = readln().toInt()
    val numbers = IntArray(n + 1)

    for (i in 0 until n) {
        numbers[i + 1] = readln().toInt()
    }

    numbers[0] = numbers[n]
    numbers.set(n, 0)

    for(i in 0 until n ){
        print("${numbers[i]} ")
    }
}