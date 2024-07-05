fun main() {
    // put your code here
    val line = readln()

    for (c in line) {
        if (c.isDigit()) {
            println(c)
            break
        }
    }

}