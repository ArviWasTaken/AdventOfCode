package nl.arviwastaken.adventofcode.year2024

import nl.arviwastaken.adventofcode.Solution
import nl.arviwastaken.adventofcode.util.retrieveExample
import nl.arviwastaken.adventofcode.util.retrieveInput

fun main() {
    val example = retrieveExample()
    println("Running examples for day")
    Day11(example).run()
    val input = retrieveInput()
    println("\nRunning inputs for day")
    Day11(input).run()
}

class Day11(input: MutableList<String>) : Solution(input) {
    override fun part1(input: MutableList<String>): String {
        var stones = input[0].split(" ").map { it.toLong() }
        for (i in 1..25) {
            val newStones = mutableListOf<Long>()
            for (stone in stones) {
                if (stone == 0L) {
                    newStones.add(1)
                } else if (stone.toString().length % 2 == 0) {
                    val length = stone.toString().length
                    val half = length / 2
                    val firstHalf = stone.toString().substring(0, half).toLong()
                    val secondHalf = stone.toString().substring(half, length).toLong()
                    newStones.add(firstHalf)
                    newStones.add(secondHalf)
                } else {
                    newStones.add(stone * 2024)
                }
            }
            stones = newStones
        }
        return stones.count().toString()
    }

    private val cache = mutableMapOf<Pair<Long, Int>, Long>()

    override fun part2(input: MutableList<String>): String {
        val stones = input[0].split(" ").map { it.toLong() }
        return stones.sumOf { stone -> calculateStones(stone, 75) }.toString()
    }

    private fun calculateStones(stone: Long, depth: Int): Long {
        return if (depth == 0) 1
        else cache.getOrPut(stone to depth) {
            val s = stone.toString()
            if (stone == 0L) calculateStones(1, depth - 1)
            else if (s.length % 2 == 0) s.chunked(s.length / 2).sumOf { calculateStones(it.toLong(), depth - 1) }
            else calculateStones(stone * 2024, depth - 1)
        }
    }
}