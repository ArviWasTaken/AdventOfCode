package nl.arviwastaken.adventofcode.year2024

import nl.arviwastaken.adventofcode.Solution
import nl.arviwastaken.adventofcode.util.retrieveExample
import nl.arviwastaken.adventofcode.util.retrieveInput

fun main() {
    val example = retrieveExample()
    println("Running examples for day")
    Day2(example).run()
    val input = retrieveInput()
    println("\nRunning inputs for day")
    Day2(input).run()
    }

class Day2(input: MutableList<String>) : Solution(input) {
    override fun part1(input: MutableList<String>): String {
        var total = 0
        for (line in input) {
            val list = mutableListOf<Int>()
            val parts = line.split(" ")
            for (part in parts) {
                list.add(part.toInt())
            }
            val diffs = getDiffs(list)
            val decreasing = diffs.all {it in -3..-1}
            val increasing = diffs.all {it in 1..3}

            if (decreasing || increasing) total ++
        }
        return total.toString()
    }

    override fun part2(input: MutableList<String>): String {
        var total = 0
        for (line in input) {
            val list = mutableListOf<Int>()
            val parts = line.split(" ")
            for (part in parts) {
                list.add(part.toInt())
            }
            val mutations = mutableListOf<Boolean>()
            for (i in list.indices) {
                val new = mutableListOf<Int>()
                new.addAll(list)
                new.removeAt(i)
                val diffs = getDiffs(new)
               mutations.add(diffs.all {it in -3..-1})
               mutations.add(diffs.all {it in 1..3})
            }

            if (mutations.any { it }) total ++
        }
        return total.toString()
    }

    fun getDiffs(values: MutableList<Int>): List<Int> {
        val diffs = mutableListOf<Int>()
        for (i in values.indices) {
            if (i == 0) continue
            diffs.add(values[i] - values[i-1])
        }
        return diffs
    }
}