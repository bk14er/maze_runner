fun <T> countElementsOfType(list: List<Any>, clazz: Class<T>): Int {
    // make your code here
    return list.count { clazz.isInstance(it) }
}