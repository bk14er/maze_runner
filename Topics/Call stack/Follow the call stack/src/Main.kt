fun printIfPrime(number: Int) {
    var isPrime = true

    for (i in 2..number / 2) {
        if (number % i == 0) {
            isPrime = false
            break
        }
    }

    if (isPrime)
        println("${number} is a prime number.")
    else println("${number} is not a prime number.")
}

fun main(args: Array<String>) {
    printIfPrime(readln().toInt())
}