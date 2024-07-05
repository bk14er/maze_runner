fun main() {
    val letter = readLine()!!.first()

    for (i in 'a'..'z') {
        if (i == letter) break
        print(i)
    }

}