package nl.arviwastaken.adventofcode.year2022

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
    private val moveValue = mapOf<String, Int>(Pair("X", 1), Pair("Y", 2), Pair("Z", 3))
    private val moveTranslate = mapOf<String, String>(Pair("X", "A"), Pair("Y", "B"), Pair("Z", "C"))
    override fun part1(input: MutableList<String>): String {
        val rounds = mutableListOf<Int>()
        for (i in input) {
            val tmp = i.split(" ")
            val moves = Pair<String, String>(tmp[0], tmp[1])
            if (moves.first == moveTranslate[moves.second]) {
                rounds.add(moveValue[moves.second]?.plus(3) ?: -1)
            } else if (moves.first == "A") {
                if (moves.second == "Y") {
                    rounds.add(moveValue[moves.second]!! + 6)
                } else {
                    rounds.add(moveValue[moves.second]!!)
                }
            } else if (moves.first == "B") {
                if (moves.second == "X") {
                    rounds.add(moveValue[moves.second]!!)
                } else {
                    rounds.add(moveValue[moves.second]!! + 6)
                }
            }else if (moves.first == "C") {
                if (moves.second == "Y") {
                    rounds.add(moveValue[moves.second]!!)
                } else {
                    rounds.add(moveValue[moves.second]!! + 6)
                }
            }
        }
        return rounds.sum().toString()
    }

    override fun part2(input: MutableList<String>): String {
        val rounds = mutableListOf<Int>()
        for (i in input) {
            val tmp = i.split(" ")
            val moves = Pair<String, String>(tmp[0], tmp[1])

            if (moves.second == "X") {
                if (moves.first == "A") {
                    rounds.add(3)
                } else  if (moves.first == "B") {
                    rounds.add(1)
                } else  if (moves.first == "C") {
                    rounds.add(2)
                }
            } else if (moves.second == "Y") {
                if (moves.first == "A") {
                    rounds.add(3 + 1)
                } else  if (moves.first == "B") {
                    rounds.add(3 + 2)
                } else  if (moves.first == "C") {
                    rounds.add(3 + 3)
                }

            } else if (moves.second == "Z") {
                if (moves.first == "A") {
                    rounds.add(6 + 2)
                } else  if (moves.first == "B") {
                    rounds.add(6 + 3)
                } else  if (moves.first == "C") {
                    rounds.add(6 + 1)
                }
            }
        }
        return rounds.sum().toString()
    }
}