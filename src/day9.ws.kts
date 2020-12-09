import kotlin.system.exitProcess

val xmasData = input("day9.txt").map(String::toLong)
val preambleLength = 25

// Part 1
val index = (preambleLength until xmasData.size).first { index ->
    val checkValue = xmasData[index]
    val preamble = xmasData.subList(index - preambleLength, index)
    preamble.none { x -> preamble.any { y -> y != x && y + x == checkValue } }
}

val part1 = xmasData[index]
println(part1)

// Part 2, Version 1
(0..xmasData.size).forEach { idx1 ->
    var sum = 0L
    val sublist = xmasData.drop(idx1).takeWhile {
        sum += it
        sum <= part1
    }
    if (sublist.sum() == part1) {
        with(sublist.sorted()) { println("${first()} + ${last()}: ${first() + last()}") }
        exitProcess(0)
    }
}

//Part 2, Version 2
(0..xmasData.size).forEach { index ->
    xmasData.drop(index).reduce { acc, l ->
        if (acc + l == part1) found(index, l)
        acc + l
    }
}

fun found(index: Int, lastItem: Long) {
    with(xmasData.subList(index, xmasData.indexOf(lastItem)).sorted()) {
        println("${first()} + ${last()}: ${first() + last()}")
    }
    exitProcess(0)
}
