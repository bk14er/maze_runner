fun main() {
    val n = readln().toInt()
    val numbers = IntArray(n)
    var statement = "NO"

    for (i in 0 until n){
        numbers[i] = readln().toInt()
    }

    val m = readln().toInt()

    for (i in 0 until n){
        if (numbers[i] == m){
            statement = "YES"
            break
        }
    }

    println(statement)
}