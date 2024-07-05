fun main() {

    val n1 = readln().toInt()
    val n2 = readln().toInt()

    if(n2 == 0){
        println("Division by zero, please fix the second argument!")
        return
    }
    println("${n1/n2}")
}