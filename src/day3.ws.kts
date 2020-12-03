val hill = input("day3.txt")
var index = 0
hill.count { row ->
    (row[index] == '#').also { index = (index + 3) % row.length }
}.let { treesHit -> println(treesHit) }
