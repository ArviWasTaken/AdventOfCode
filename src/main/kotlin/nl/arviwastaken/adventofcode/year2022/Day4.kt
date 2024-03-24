package nl.arviwastaken.adventofcode.year2022

import nl.arviwastaken.adventofcode.Solution
import nl.arviwastaken.adventofcode.util.retrieveExample
import nl.arviwastaken.adventofcode.util.retrieveInput

fun main() {
    val example = retrieveExample()
    println("Running examples for day")
    Day4(example).run()
    val input = retrieveInput()
    println("\nRunning inputs for day")
    Day4(input).run()
    }


class Day4(input: MutableList<String>) : Solution(input) {
    override fun part1(input: MutableList<String>): String {
        var assignments = 0
        for (s in input) {
            val split = s.split(",")
            val first = split[0].split("-")
            val last = split[1].split("-")

            val firstPair = IntRange(first[0].toInt(), first[1].toInt())
            val lastpair = IntRange(last[0].toInt(), last[1].toInt())

            if (firstPair.contains(lastpair) || lastpair.contains(firstPair)) {
                assignments++
            }
        }
        return assignments.toString()
    }

    override fun part2(input: MutableList<String>): String {
        var assignments = 0
        for (s in input) {
            val split = s.split(",")
            val first = split[0].split("-")
            val last = split[1].split("-")

            val firstPair = IntRange(first[0].toInt(), first[1].toInt())
            val lastpair = IntRange(last[0].toInt(), last[1].toInt())

            if (lastpair.contains(firstPair.last) || lastpair.contains(firstPair.first) || firstPair.contains(lastpair.first) || firstPair.contains(lastpair.first)) {
                assignments++
            }
        }
        return assignments.toString()
    }
}

fun IntRange.contains(range: IntRange): Boolean = this.contains(range.first) && this.contains(range.last)