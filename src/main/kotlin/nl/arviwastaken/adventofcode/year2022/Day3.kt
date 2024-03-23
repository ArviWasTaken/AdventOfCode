package nl.arviwastaken.adventofcode.year2022

import nl.arviwastaken.adventofcode.Solution
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
        val rugSacksCommonItems = mutableListOf<Char>()
        for (s in input) {
            val first = s.substring(0, s.length/2)
            val second = s.substring(s.length/2)

            var rugsackCommon = '+'
            for (c in first) {
                if (second.contains(c)) rugsackCommon = c
            }
            rugSacksCommonItems.add(rugsackCommon)

        }

        val values = rugSacksCommonItems.map{
            if (it.code >=  97) {
                it.code - 96
            }  else {
                it.code - 64 + 26
            }
        }

       return values.sum().toString()
    }

    override fun part2(input: MutableList<String>): String {
        val rugSacksCommonItems = mutableListOf<Char>()

        for (i in 0..<input.size/3 ) {
            val index = i*3
            val first = input[index]
            val second = input[index+1]
            val third = input[index+2]

            var rugsackCommon = '+'
            for (c in first) {
                if (second.contains(c) && third.contains(c)) rugsackCommon = c
            }
            rugSacksCommonItems.add(rugsackCommon)
        }

        val values = rugSacksCommonItems.map{
            if (it.code >=  97) {
                it.code - 96
            }  else {
                it.code - 64 + 26
            }
        }

        return values.sum().toString()
    }
}