package nl.arviwastaken.adventofcode.year2022

import nl.arviwastaken.adventofcode.Solution
import nl.arviwastaken.adventofcode.util.getDigits
import nl.arviwastaken.adventofcode.util.retrieveExample
import nl.arviwastaken.adventofcode.util.retrieveInput

fun main() {
    val example = retrieveExample()
    println("Running examples for day")
    Day5(example).run()
    val input = retrieveInput()
    println("\nRunning inputs for day")
    Day5(input).run()
}


class Day5(input: MutableList<String>) : Solution(input) {
    override fun part1(input: MutableList<String>): String {
        var firstPart = true
        val levels = mutableListOf<String>()
        var commands = mutableListOf<String>()
        var length = 0
        for (s in input) {
            if (firstPart) {
                if (s.isEmpty()) {
                    firstPart = false
                    continue
                }
                if (s.getDigits().isNotEmpty())
                    length = s.length
                if (s.contains(Regex("[A-Z]+")))
                    levels.addFirst(s)
            } else {
                commands.add(s)
            }
        }

        val stacks = mutableListOf<String>()
        for (i in 0..<length step 4) {
            var newLevel = ""
            for (l in levels) {
                if (l[1 + i].isLetter())
                    newLevel += l[1 + i]
            }
            stacks.add(newLevel)
        }
        val newCommands = commands.map { it.split(' ') }.toMutableList()

        for (c in newCommands) {
            val amountToTake = c[1].getDigits().toInt()
            val beginStackIndex = c[3].getDigits().toInt() - 1
            val endStackIndex = c[5].getDigits().toInt() - 1

            var beginStack = stacks[beginStackIndex]
            var endStack = stacks[endStackIndex]
            val take = beginStack.substring(beginStack.length - amountToTake)

            beginStack = beginStack.substring(0, beginStack.length - amountToTake)
            endStack += take.reversed()

            stacks[beginStackIndex] = beginStack
            stacks[endStackIndex] = endStack
        }

        var answer = ""
        stacks.forEach { answer += it.last() }

        return answer
    }

    override fun part2(input: MutableList<String>): String {
        var firstPart = true
        val levels = mutableListOf<String>()
        var commands = mutableListOf<String>()
        var length = 0
        for (s in input) {
            if (firstPart) {
                if (s.isEmpty()) {
                    firstPart = false
                    continue
                }
                if (s.getDigits().isNotEmpty())
                    length = s.length
                if (s.contains(Regex("[A-Z]+")))
                    levels.addFirst(s)
            } else {
                commands.add(s)
            }
        }

        val stacks = mutableListOf<String>()
        for (i in 0..<length step 4) {
            var newLevel = ""
            for (l in levels) {
                if (l[1 + i].isLetter())
                    newLevel += l[1 + i]
            }
            stacks.add(newLevel)
        }
        val newCommands = commands.map { it.split(' ') }.toMutableList()

        for (c in newCommands) {
            val amountToTake = c[1].getDigits().toInt()
            val beginStackIndex = c[3].getDigits().toInt() - 1
            val endStackIndex = c[5].getDigits().toInt() - 1

            var beginStack = stacks[beginStackIndex]
            var endStack = stacks[endStackIndex]
            val take = beginStack.substring(beginStack.length - amountToTake)

            beginStack = beginStack.substring(0, beginStack.length - amountToTake)
            endStack += take

            stacks[beginStackIndex] = beginStack
            stacks[endStackIndex] = endStack
        }

        var answer = ""
        stacks.forEach { answer += it.last() }

        return answer
    }

}