fun f(a: Int, b: Int, c: Int) = a + b + c


fun main() {
    f(a = 10, c = 12, b = 11)
    f(10, b = 11, 12)
    f(a = 10, b = 11, 12)
    f(10, 11, c = 12)
}