fun main() {
    var n = readln()!!.toInt()
    var repetation = 1

    while (n > 0){
        repeat(repetation){

            if(n > 0) print("$repetation ")
            n--
        }
        repetation++
    }
}