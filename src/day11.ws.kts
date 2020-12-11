var seats = input("day11.txt")

while (true) {
    val updated = seats.mapIndexed { idx1, line ->
        line.mapIndexed { idx2, seat ->
            val adjacentSeats = (idx1 - 1..idx1 + 1).flatMap { x ->
                (idx2 - 1..idx2 + 1).map { y ->
                    if (x == idx1 && y == idx2) 'X' else seats.getXY(x, y)
                }.minus('X') // Remove actual seat (center matrix item)
            }
            val occupiedCount = adjacentSeats.count { it == '#' }
            when {
                seat == 'L' && occupiedCount == 0 -> '#'
                seat == '#' && occupiedCount >= 4 -> 'L'
                else -> seat
            }
        }.joinToString("")
    }
    if (updated == seats) break else seats = updated
}

fun List<String>.getXY(x: Int, y: Int) = this.getOrNull(x)?.getOrNull(y)

println(seats.sumBy { row -> row.count { seat -> seat == '#' } })

// Part 2
seats = input("day11.txt")
while (true) {
    val updated = seats.mapIndexed { idx1, line ->
        line.mapIndexed { idx2, seat ->
            val nw = scanUntilEdge(idx1, idx2, { x -> x - 1 }, { y -> y - 1 })
            val n = scanUntilEdge(idx1, idx2, { x -> x }, { y -> y - 1 })
            val ne = scanUntilEdge(idx1, idx2, { x -> x + 1 }, { y -> y - 1 })
            val w = scanUntilEdge(idx1, idx2, { x -> x - 1 }, { y -> y })
            val e = scanUntilEdge(idx1, idx2, { x -> x + 1 }, { y -> y })
            val sw = scanUntilEdge(idx1, idx2, { x -> x - 1 }, { y -> y + 1 })
            val s = scanUntilEdge(idx1, idx2, { x -> x }, { y -> y + 1 })
            val se = scanUntilEdge(idx1, idx2, { x -> x + 1 }, { y -> y + 1 })

            val occupiedCount = listOf(nw, n, ne, w, e, sw, s, se).count { it }

            when {
                seat == 'L' && occupiedCount == 0 -> '#'
                seat == '#' && occupiedCount >= 5 -> 'L'
                else -> seat
            }
        }.joinToString("")
    }
    if (updated == seats) break else seats = updated
}

println(seats.sumBy { row -> row.count { seat -> seat == '#' } })

fun scanUntilEdge(xStart: Int, yStart: Int, xMod: (Int) -> Int, yMod: (Int) -> Int): Boolean {
    val xMax = seats.size -1
    val yMax = seats[0].length - 1

    var x = xMod(xStart)
    var y = yMod(yStart)

    while (x in 0..xMax && y in 0..yMax) {
        when (seats[x][y]) {
            '#' -> return true
            'L' -> return false
        }
        x = xMod(x)
        y = yMod(y)
    }
    return false
}
