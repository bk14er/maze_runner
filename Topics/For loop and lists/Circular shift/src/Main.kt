fun main() {
    // write your code here
    val n = readLine()!!.toInt()
    val list = MutableList(n) { readLine()!!.toInt() }

    list.add(0,list.removeLast())

    println(list.joinToString(" "))
}