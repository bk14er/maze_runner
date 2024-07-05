fun main() {
    //Do not touch code below
    var inputArray: Array<Array<String>> = arrayOf()
    val n = readLine()!!.toInt()
    for (i in 0 until n) {
        val strings = readLine()!!.split(' ').toTypedArray()
        inputArray += strings
    }
    // write your code here


    if (n > 1) {
        val lastIndex = inputArray.lastIndex

        val newInputArray: Array<Array<String>> = arrayOf(
            inputArray[lastIndex],
            inputArray[0]
        )

        println(newInputArray.contentDeepToString())
    }

}