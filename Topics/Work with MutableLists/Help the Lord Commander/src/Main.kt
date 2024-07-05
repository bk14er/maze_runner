fun main() {  
    val beyondTheWall = readLine()!!.split(", ").map { it }.toMutableList()
    val backToTheWall = readLine()!!.split(", ").map { it }.toMutableList()   
    // do not touch the lines above
    // write your code here

    var result = true

    for (onDuty in beyondTheWall){
        if(!backToTheWall.contains(onDuty)){
            result = false
            break
        }
    }

    println(result)

}


fun reversePrint() {
    val numbers = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    for (index in numbers.lastIndex downTo  0 step  2) {
        println("$index: ${numbers[index]}")
    }
}