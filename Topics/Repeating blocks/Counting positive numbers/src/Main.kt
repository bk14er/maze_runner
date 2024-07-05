fun main() {
    // put your code here
    val n = readLine()!!.toInt()

    var positive = 0

    repeat(n){
        val next = readLine()!!.toInt()

        if(next > 0){
            positive++
        }

    }

    print(positive)

}