fun main() {
    val n = readLine()!!.toInt()
    var sqrt = 1;

    do{
        println(sqrt * sqrt)
        sqrt++
    }
    while (sqrt * sqrt <= n)

}