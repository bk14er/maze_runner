import java.util.*

fun main() {
    val a = readln().trim().toDouble()
    val b = readln().trim().toDouble()
    val c = readln().trim().toDouble()
    val d = readln().trim().toDouble()

    Locale.setDefault(Locale.US)

    val format = "%,12.2f"

    print(String.format(format,a))
    println(String.format(format,b))
    print(String.format(format,c))
    println(String.format(format,d))
}