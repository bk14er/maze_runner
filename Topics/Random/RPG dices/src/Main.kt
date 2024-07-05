import kotlin.random.Random

fun rpgDices(faces1: Int, faces2: Int): Int {
    // write your code here
    if (faces1 <= 0 || faces2 <= 0) {
        return -1 // You can choose a suitable value to represent an error
    }

    // Roll two dice and return the sum
    val result1 = Random.nextInt(1, faces1 + 1)
    val result2 = Random.nextInt(1, faces2 + 1)
    val totalSum = result1 + result2

    return totalSum
}