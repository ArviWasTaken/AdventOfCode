package nl.arviwastaken.adventofcode.year2022

import nl.arviwastaken.adventofcode.Solution
import nl.arviwastaken.adventofcode.util.retrieveExample
import nl.arviwastaken.adventofcode.util.retrieveInput

fun main() {
    val example = retrieveExample()
    println("Running examples for day")
    Day10(example).run()
    val input = retrieveInput()
    println("\nRunning inputs for day")
    Day10(input).run()
    }


class Day10(input: MutableList<String>) : Solution(input) {
    override fun part1(input: MutableList<String>): String {
        var cycles = 0
        var x = 1
        var totalSignalStrength = 0
        for (line in input) {
            val parts = line.split(" ")
            var amountOfCyclesForOperation = 0
            var add: Int? =  null
            when (parts[0]) {
                "noop" -> amountOfCyclesForOperation = 1
                "addx" -> {
                    amountOfCyclesForOperation = 2
                    add = parts[1].toInt()
                }
            }
            for (i in 1..amountOfCyclesForOperation) {
                cycles++
                if (cycles == 20 || (cycles - 20) % 40 == 0) {
                    val signalStrength = x * cycles
                    totalSignalStrength += signalStrength
                }
                if (i == 2) {
                    x += add!!
                }

            }
        }
        return totalSignalStrength.toString()
    }

    override fun part2(input: MutableList<String>): String {
        var cycle = 0
        var x = 1
        for (line in input) {
            val parts = line.split(" ")
            var amountOfCyclesForOperation = 0
            var add: Int? =  null
            when (parts[0]) {
                "noop" -> amountOfCyclesForOperation = 1
                "addx" -> {
                    amountOfCyclesForOperation = 2
                    add = parts[1].toInt()
                }
            }
            for (i in 1..amountOfCyclesForOperation) {
                if (cycle%40 in x-1 .. x+1) {
                    print("#")
                } else {
                    print(".")
                }
                cycle++

                if (i == 2) {
                    x += add!!
                }
                if (cycle% 40 == 0) {
                    println()
                }
            }
        }
        return "^^^^^^^look above^^^^^^^^"
    }
}