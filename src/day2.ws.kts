input("day2.txt").count { item ->
    val (counts, letter, input) = item.split(" ")
    val (min, max) = counts.split("-").map { it.toInt() }
    input.count { it == letter[0] } in min..max
}.let { total -> println(total) }
