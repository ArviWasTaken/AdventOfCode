package nl.arviwastaken.adventofcode.year2022

import nl.arviwastaken.adventofcode.Solution
import nl.arviwastaken.adventofcode.util.retrieveExample
import nl.arviwastaken.adventofcode.util.retrieveInput

fun main() {
    val example = retrieveExample()
    println("Running examples for day")
    Day6(example).run()
    val input = retrieveInput()
    println("\nRunning inputs for day")
    Day6(input).run()
}


class Day6(input: MutableList<String>) : Solution(input) {
    override fun part1(input: MutableList<String>): String {
        val line = input[0]
        var buffer = ""
        for (i in 0..line.length) {
            val current = line[i]
            if (i < 3) {
                buffer += current
                continue
            }
            buffer += current
            if (buffer.toSet().size == 4) {
                return (i + 1).toString()
            }
            buffer = buffer.removeRange(0, 1)
        }
        return "-0"
    }

    override fun part2(input: MutableList<String>): String {
        val line = input[0]
        var buffer = ""
        for (i in 0..line.length) {
            val current = line[i]
            if (i < 13) {
                buffer += current
                continue
            }
            buffer += current
            if (buffer.toSet().size == 14) {
                return (i + 1).toString()
            }
            buffer = buffer.removeRange(0, 1)
        }
        return "-0"
    }
}
