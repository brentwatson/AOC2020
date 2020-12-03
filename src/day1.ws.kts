val first = input("day1.txt").map { it.toInt() }
val second = listOf<Int>() + first
first.forEachIndexed { idx1, item1 ->
    second.forEachIndexed { idx2, item2 ->
        if (idx1 != idx2 && item1 + item2 == 2020) {
            println("${item1 * item2}")
        }
    }
}
