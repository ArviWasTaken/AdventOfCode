package nl.arviwastaken.adventofcode.year2024

import nl.arviwastaken.adventofcode.Solution
import nl.arviwastaken.adventofcode.util.getDigits
import nl.arviwastaken.adventofcode.util.retrieveExample
import nl.arviwastaken.adventofcode.util.retrieveInput

fun main() {
    val example = retrieveExample()
    println("Running examples for day")
    Day1(example).run()
    val input = retrieveInput()
    println("\nRunning inputs for day")
    Day1(input).run()
    }


class Day1(input: MutableList<String>) : Solution(input) {
    override fun part1(input: MutableList<String>): String {
       val left = mutableListOf<Int>()
       val right = mutableListOf<Int>()
        for (line in input) {
            val parts = line.split("  ")
            left.add(parts[0].getDigits().toInt())
            right.add(parts[1].getDigits().toInt())
        }
        left.sort()
        right.sort()
        var sum = 0
        for (i in left.indices) {
            val diff = if (left[i] > right[i])  left[i] - right[i] else right[i] - left[i]
            sum += diff
        }

        return sum.toString()
    }

    override fun part2(input: MutableList<String>): String {
        val left = mutableListOf<Int>()
        val right = mutableListOf<Int>()
        for (line in input) {
            val parts = line.split("  ")
            left.add(parts[0].getDigits().toInt())
            right.add(parts[1].getDigits().toInt())
        }
        var sum = 0
        for (num in left) {
            val count = right.count { it == num }
            sum += num * count
        }

        return sum.toString()
    }

}