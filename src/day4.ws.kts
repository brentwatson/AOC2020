val required = listOf("byr:", "iyr:", "eyr:", "hgt:", "hcl:", "ecl:", "pid:")
input("day4.txt")
    .fold(listOf("")) { acc, line ->
        if (line.isBlank()) acc + "" else acc.dropLast(1) + "${acc.last()} $line"
    }
    .count { line -> required.all { it in line } }
    .let { valid -> println(valid) }
