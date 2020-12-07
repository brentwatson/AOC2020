data class Bag(val name: String, val contains: List<String>)

// Input data to Tree
val bags = input("day7.txt").map { line ->
    val (name, containsStr) = line.split(" bags ")
    val contains = containsStr
        .replace("contain ", "")
        .split(", ")
        .map { it.replace("\\d ".toRegex(), "").replace(" bag(.*)".toRegex(), "") }
    Bag(name, contains)
}

tailrec fun find(bagName: List<String>, canHold: Set<String> = setOf()): Int {
    val found = bags.filter { bag -> bagName.any { it in bag.contains } }.map { it.name }
    return if (found.isEmpty()) canHold.size else find(found, canHold + found)
}

println(find(listOf("shiny gold")))
