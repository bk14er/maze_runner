import kotlin.random.Random

fun makeDecision(): String {
    // write here
    val options = arrayOf("Rock", "Paper", "Scissors")

    return options[Random.nextInt(0, 3)]

}