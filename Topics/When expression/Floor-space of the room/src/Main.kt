const val PI = 3.14

fun main() {
    val shape = readln()!!

    val arena = when (shape) {
        "triangle" -> {
            val a = readDoubleInput()
            val b = readDoubleInput()
            val c = readDoubleInput()
            calculateTriangleArea(a, b, c)
        }

        "rectangle" -> {
            val a = readDoubleInput()
            val b = readDoubleInput()
            calculateRectangleArea(a, b)
        }

        "circle" -> {
            val r = readDoubleInput()
            calculateCircleArea(r)
        }

        else -> 0.0
    }

    println(arena)
}

fun readDoubleInput(): Double {
    return readLine()?.toDouble() ?: 0.0
}

fun calculateTriangleArea(a: Double, b: Double, c: Double): Double {
    val s = (a + b + c) / 2
    return Math.sqrt(s * (s - a) * (s - b) * (s - c))
}

fun calculateRectangleArea(a: Double, b: Double): Double {
    return a * b
}

fun calculateCircleArea(r: Double): Double {
    return 3.14 * r * r
}