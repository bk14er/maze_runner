import kotlin.random.Random

fun generatePredictablePassword(seed: Int): String {
    var randomPassword = ""

    val generator = Random(seed)

    for(i in 1..10) {
        randomPassword += generator.nextInt(33,127).toChar()
    }

	return randomPassword
}