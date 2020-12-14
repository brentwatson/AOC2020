import kotlin.math.abs

data class State(var x: Int = 0, var y: Int = 0, val degrees: Int = 90 /* Start Facing east */)

input("day12.txt").fold(State()) { state, line ->
    val direction = line.first()
    val amount = line.drop(1)
    when (direction) {
        'E' -> state.copy(x = state.x + amount.toInt())
        'S' -> state.copy(y = state.y - amount.toInt())
        'W' -> state.copy(x = state.x - amount.toInt())
        'N' -> state.copy(y = state.y + amount.toInt())
        'R' -> state.copy(degrees = state.degrees + amount.toInt())
        'L' -> state.copy(degrees = state.degrees - amount.toInt())
        'F' -> when(state.degrees % 360) {
            90, -270 -> state.copy(x = state.x + amount.toInt())
            180, -180 -> state.copy(y = state.y - amount.toInt())
            270, -90 -> state.copy(x = state.x - amount.toInt())
            0 -> state.copy(y = state.y + amount.toInt())
            else -> error("unexpected degrees ${state.degrees}")
        }
        else -> error("Unknown direction: $direction")
    }
}.let { println(abs(it.x) + abs(it.y)) }