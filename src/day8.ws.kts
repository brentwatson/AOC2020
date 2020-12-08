val instructions = input("day8.txt")
val indexesUsed = mutableSetOf(0)

fun run(instructions: List<String>): Result {
    var accumulator = 0
    var index = 0
    do {
        val (instruction, value) = instructions[index].split(" ")
        when (instruction) {
            "nop" -> index++
            "acc" -> { index++; accumulator += value.toInt() }
            "jmp" -> index += value.toInt()
            else -> error("unknown instruction: $instruction")
        }
    } while (index < instructions.size && indexesUsed.add(index))
    return Result(accumulator, !indexesUsed.contains(index))
}

// Part 1
println(run(instructions).accumulator)

// Part 2
indexesUsed.toSet().forEach { index ->
    indexesUsed.clear()
    val (instruction, value) = instructions[index].split(" ")
    val result = when (instruction) {
        "nop" -> run(instructions.toMutableList().also { it[index] = "jmp $value" } )
        "jmp" -> run(instructions.toMutableList().also { it[index] = "nop $value" } )
        else -> Result(-1, false)
    }
    if (result.completed) { // Completed to end of file.
        println(result.accumulator)
    }
}

data class Result(val accumulator: Int, val completed: Boolean)
