// write your code here

/* Do not change code below */
fun main() {
    val a = readLine()!!.toInt()
    println(getLastDigit(a))
}

private fun getLastDigit(a:Int) = Math.abs(a % 10)