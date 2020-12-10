// Part 1
var one = 0
var three = 1
input("day10.txt")
    .map(String::toInt)
    .sorted()
    .fold(0) { last, i ->
        if (i - last == 1) one++ else if (i - last == 3) three++
        i
    }
println(one * three)

// Part 2 (WIP)
val adapters = input("day10.txt").map(String::toLong).sorted()
val items = listOf(0L) + adapters + (adapters.last() + 3)
items.mapIndexed { index, item ->
    (1..3).count { items.getOrMax(index+it) <= item + 3}
}.let { println(it.reduce { acc, i -> if(i != 0) acc * i else acc }) }

fun List<Long>.getOrMax(index: Int) = this.getOrElse(index) { Long.MAX_VALUE }
