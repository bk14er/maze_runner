fun main() {    
    var inputArray: Array<Array<String>> = arrayOf()
    val n = readLine()!!.toInt()
    for (i in 0 until n) {
        val strings = readLine()!!.split(' ').toTypedArray()
        inputArray += strings
    }
    // Do not change lines above
    // Write your code here

    if(n > 2){
        println(inputArray[2].joinToString())
    }


}