package nl.arviwastaken.adventofcode.year2022

import nl.arviwastaken.adventofcode.Solution
import nl.arviwastaken.adventofcode.util.retrieveExample
import nl.arviwastaken.adventofcode.util.retrieveInput
import kotlin.math.abs

fun main() {
    val example = retrieveExample()
    println("Running examples for day")
    Day9(example).run()
    val input = retrieveInput()
    println("\nRunning inputs for day")
    Day9(input).run()
}


class Day9(input: MutableList<String>) : Solution(input) {
    override fun part1(input: MutableList<String>): String {
        var tail = Pair(0, 0)
        var head = Pair(0, 0)
        val visitedLocations = mutableSetOf<Pair<Int, Int>>()

        for (line in input) {
            val parts = line.split(" ")
            val direction = parts[0]
            val distance = parts[1].toInt()
            for (i in 0..<distance) {
                when (direction) {
                    "R" -> head = Pair(head.first + 1, head.second)
                    "L" -> head = Pair(head.first - 1, head.second)
                    "U" -> head = Pair(head.first, head.second + 1)
                    "D" -> head = Pair(head.first, head.second - 1)
                }

                val xDiff = abs(tail.first - head.first)
                val yDiff = abs(tail.second - head.second)

                if (xDiff > 1 && tail.second != head.second) {
                    tail = if (tail.first < head.first) {
                        if (tail.second < head.second) {
                            Pair(tail.first + 1, tail.second + 1)
                        } else {
                            Pair(tail.first + 1, tail.second - 1)
                        }
                    } else {
                        if (tail.second < head.second) {
                            Pair(tail.first - 1, tail.second + 1)
                        } else {
                            Pair(tail.first - 1, tail.second - 1)
                        }
                    }
                } else if (xDiff > 1) {
                    tail = if (tail.first < head.first) {
                        Pair(tail.first + 1, tail.second)
                    } else {
                        Pair(tail.first - 1, tail.second)
                    }
                }

                if (yDiff > 1 && tail.first != head.first) {
                    tail = if (tail.second < head.second) {
                        if (tail.first < head.first) {
                            Pair(tail.first + 1, tail.second + 1)
                        } else {
                            Pair(tail.first - 1, tail.second + 1)
                        }
                    } else {
                        if (tail.first < head.first) {
                            Pair(tail.first + 1, tail.second - 1)
                        } else {
                            Pair(tail.first - 1, tail.second - 1)
                        }
                    }
                } else if (yDiff > 1) {
                    tail = if (tail.second < head.second) {
                        Pair(tail.first, tail.second + 1)
                    } else {
                        Pair(tail.first, tail.second - 1)
                    }
                }


                visitedLocations.add(tail)
            }
        }
        return visitedLocations.size.toString()
    }

    override fun part2(input: MutableList<String>): String {
        var tail9 = Pair(0, 0)
        var tail8 = Pair(0, 0)
        var tail7 = Pair(0, 0)
        var tail6 = Pair(0, 0)
        var tail5 = Pair(0, 0)
        var tail4 = Pair(0, 0)
        var tail3 = Pair(0, 0)
        var tail2 = Pair(0, 0)
        var tail1 = Pair(0, 0)
        var head = Pair(0, 0)
        val visitedLocations = mutableSetOf<Pair<Int, Int>>()

        for (line in input) {
            val parts = line.split(" ")
            val direction = parts[0]
            val distance = parts[1].toInt()
            for (i in 0..<distance) {
                when (direction) {
                    "R" -> head = Pair(head.first + 1, head.second)
                    "L" -> head = Pair(head.first - 1, head.second)
                    "U" -> head = Pair(head.first, head.second + 1)
                    "D" -> head = Pair(head.first, head.second - 1)
                }

                tail1 = calculateNewPosition(tail1, head)
                tail2 = calculateNewPosition(tail2, tail1)
                tail3 = calculateNewPosition(tail3, tail2)
                tail4 = calculateNewPosition(tail4, tail3)
                tail5 = calculateNewPosition(tail5, tail4)
                tail6 = calculateNewPosition(tail6, tail5)
                tail7 = calculateNewPosition(tail7, tail6)
                tail8 = calculateNewPosition(tail8, tail7)
                tail9 = calculateNewPosition(tail9, tail8)

                visitedLocations.add(tail9)
            }
        }
        return visitedLocations.size.toString()
    }
}

fun calculateNewPosition(tail: Pair<Int, Int>, head: Pair<Int, Int>): Pair<Int, Int> {
    val xDiff = abs(tail.first - head.first)
    val yDiff = abs(tail.second - head.second)

    return if ((xDiff > 1 && yDiff > 0) || (yDiff > 1 && xDiff > 0)) {
        val x = whichDirection(tail.first, head.first)
        val y = whichDirection(tail.second, head.second)
        Pair(x, y)
    } else if (xDiff > 1) {
        Pair(whichDirection(tail.first, head.first), tail.second)
    } else if (yDiff > 1) {
        Pair(tail.first, whichDirection(tail.second, head.second))
    } else {
        tail
    }
}

fun whichDirection(first: Int, second: Int): Int {
    return if (first < second) {
        first + 1
    } else {
        first - 1
    }
}