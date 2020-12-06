input("day5.txt").map {
    val row = it.take(7).fold(0..127) { acc, c ->
        val mid = (acc.first + acc.last) / 2
        if (c == 'F') acc.first..mid else (mid + 1)..acc.last
    }.first
    val column = it.takeLast(3).fold(0..7) { acc, c ->
        val mid = (acc.first + acc.last) / 2
        if (c == 'L') acc.first..mid else (mid + 1)..acc.last
    }.first

    row * 8 + column
}.maxOrNull().let { println(it) }
