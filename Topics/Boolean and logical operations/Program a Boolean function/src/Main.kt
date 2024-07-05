fun main() {
    val x = readLine().toBoolean()
    val y = readLine().toBoolean()
    val z = readLine().toBoolean()

    println("${!(x && y) || z}")
}