fun main() {
    // write your code here
    val n = readln().toInt()
    val numbers = mutableListOf<Int>()
    val numbersShifted = MutableList(n){0}

    repeat(n) {
        numbers.add(readln().toInt())
    }

    val shift = readln().toInt() % n


    if(shift == 0){
        println(numbers.joinToString(" "))
        return
    }

    for (i in 0 until n) {
        val index = (i + shift) % n
        numbersShifted[index] = numbers[i]
    }


    println(numbersShifted.joinToString(" "))
}