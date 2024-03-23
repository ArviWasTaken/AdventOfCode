package nl.arviwastaken.adventofcode.year2022

import nl.arviwastaken.adventofcode.Solution
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
        val elves = mutableListOf<List<Int>>()
        var tmp = mutableListOf<Int>()
        for (i in input) {
            if (i.isNotEmpty()) {
                tmp.add(i.toInt())
            }
             else {
                elves.add(tmp)
                tmp = mutableListOf()
            }
        }
        val sums = mutableListOf<Int>()
        elves.forEach { sums.add(it.sum()) }
        return sums.max().toString()
    }

    override fun part2(input: MutableList<String>): String {
        val elves = mutableListOf<List<Int>>()
        var tmp = mutableListOf<Int>()
        for (i in input) {
            if (i.isNotEmpty()) {
                tmp.add(i.toInt())
            }
            else {
                elves.add(tmp)
                tmp = mutableListOf()
            }
        }
        val sums = mutableListOf<Int>()
        elves.forEach { sums.add(it.sum()) }
        val highestSums = mutableListOf<Int>()
        repeat(3) {
            val max = sums.max()
            highestSums.add(max)
            sums.remove(max)
        }
        return highestSums.sum().toString()
    }

}