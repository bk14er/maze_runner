fun main() {
    val obj1 = Any()
    val obj2 = Any()
    val str1 = "Hello"
    val str2 = "Hello"
    val isEqual = (str1 == str2 && obj1 === obj2)
    val result = "The strings are structurally equal and objects are referentiall equal: $isEqual"
    println(result)
}