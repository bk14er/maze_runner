fun validatePassword(password: String): Boolean {
    if (password.length >= 5) {
        println("Good password")
        return true
    }
    println("Your password should be five or longer characters, please write a new password")
    return false
}

// do not change the code below
fun main() {
    var validationResult = false
    while (!validationResult) {
        val password = readln()
        validationResult = validatePassword(password)
    }
}