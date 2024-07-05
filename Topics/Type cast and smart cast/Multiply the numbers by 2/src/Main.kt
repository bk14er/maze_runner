fun multiplyInts(list: List<Any>): List<Any> {
    val result = mutableListOf<Any>()
    for (item in list) {
        val newItem = when (item) {
            is Int -> item * 2
            else -> item
        }
        result.add(newItem)
    }
    return result
}