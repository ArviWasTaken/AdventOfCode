package nl.arviwastaken.adventofcode.year2024

import nl.arviwastaken.adventofcode.Solution
import nl.arviwastaken.adventofcode.util.getDigits
import nl.arviwastaken.adventofcode.util.retrieveExample
import nl.arviwastaken.adventofcode.util.retrieveInput

fun main() {
    val example = retrieveExample()
    println("Running examples for day")
    Day3(example).run()
    val input = retrieveInput()
    println("\nRunning inputs for day")
    Day3(input).run()
    }

class Day3(input: MutableList<String>) : Solution(input) {
    override fun part1(input: MutableList<String>): String {
        var total = 0
        for (line in input) {
            val parts = Regex("mul\\((\\d+),(\\d+)\\)").findAll(line).toList()
            for (part in parts) {
                val first = part.groups[1]?.value?.getDigits()?.toInt()
                val second = part.groups[2]?.value?.getDigits()?.toInt()
                total += first!! * second!!
            }
        }
        return total.toString()
    }

    override fun part2(input: MutableList<String>): String {
        var total = 0
        var enabled = true
        for (line in input) {
            val parts = Regex("mul\\((\\d+),(\\d+)\\)|do\\(\\)|don't\\(\\)").findAll(line).toList()
            for (part in parts) {
                val operation = part.groups[0]?.value ?: ""
                when (operation) {
                    "" -> println(operation)
                    "do()" -> enabled = true
                    "don't()" -> enabled = false
                    else -> {
                        if (enabled) {
                            val first = part.groups[1]?.value?.getDigits()?.toInt()
                            val second = part.groups[2]?.value?.getDigits()?.toInt()
                            total += first!! * second!!
                        }
                    }
                }
            }
        }
        return total.toString()
    }
}