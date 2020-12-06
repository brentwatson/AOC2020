input("day6.txt")
    .fold(listOf("")) { acc, line ->
        if (line.isBlank()) acc + "" else acc.dropLast(1) + "${acc.last()}$line"
    }
    .map { it.groupBy { x -> x } }
    .map { it.keys.size }
    .sum()
    .let { println(it) }

// Part 2
input("day6.txt")
    .fold(listOf(Pair(0, ""))) { acc, line ->
        if (line.isBlank()) acc + Pair(0, "") else acc.dropLast(1) + Pair(acc.last().first+1, "${acc.last().second}$line")
    }
    .sumOf { (rows, input) ->
        input.groupBy { it }.filter { it.value.size == rows }.size
    }
    .let { println(it) }
