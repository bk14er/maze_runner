fun main() {
    // put your code here
    val a = readln()!!.toInt()
    val b = readln()!!.toInt()
    val c = readln()!!.toInt()

    val ab = greaterThen20(a, b)
    val ac = greaterThen20(a, c)
    val bc = greaterThen20(b, c)

    println("${ab || bc || ac}")
}

private fun greaterThen20(a: Int, b: Int) = a + b == 20