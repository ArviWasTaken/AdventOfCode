package nl.arviwastaken.adventofcode

abstract class Solution(private val input: MutableList<String>) {

    fun run() {
        println("Part 1 answer: ${part1(input)}")
        println("Part 2 answer: ${part2(input)}")
    }

    abstract fun part1(input: MutableList<String>): String
    abstract fun part2(input: MutableList<String>): String
}