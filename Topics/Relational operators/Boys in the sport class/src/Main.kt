fun main() {
    // put your code here
    val h1 = readln()!!.toInt()
    val h2 = readln()!!.toInt()
    val h3 = readln()!!.toInt()

    val correctlyArranged = (h2 in h3..h1)
            || (h2 in h1..h3)

    println(correctlyArranged)

}