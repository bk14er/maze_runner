fun countStrings(list: List<Any>): Int {
    return list.count { it is String }
}