package nl.arviwastaken.adventofcode.year2024

import nl.arviwastaken.adventofcode.Solution
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
        var total = 0
        var isRulesSection = true
        val rulesMap = mutableMapOf<Int, MutableList<Int>>()
        for (line in input) {
            if (isRulesSection) {
                if (line.isEmpty()) {
                    isRulesSection = false
                } else {
                    val parts = line.split("|")
                    val page = parts[0].toInt()
                    val rule = parts[1].toInt()
                    val existing = rulesMap[page]
                    if (existing != null) {
                        existing.add(rule)
                        rulesMap[page] = existing
                    } else {
                        rulesMap[page] = mutableListOf(rule)
                    }
                }
            } else {
                val parts = line.split(",")
                var rightOrder = true
                for (i in parts.indices) {
                    val page = parts[i].toInt()
                    val rules = rulesMap[page]
                    if (rules != null) {
                        for (rule in rules) {
                            if (parts.contains(rule.toString())) {
                                if (i > parts.indexOf(rule.toString())) {
                                    rightOrder = false
                                }
                            }
                        }
                    }
                }
                if (rightOrder) {
                    total += parts[parts.size / 2].toInt()
                }
            }
        }
        return total.toString()
    }

    override fun part2(input: MutableList<String>): String {
        var total = 0
        var isRulesSection = true
        val rulesMap = mutableMapOf<Int, MutableList<Int>>()
        for (line in input) {
            if (isRulesSection) {
                if (line.isEmpty()) {
                    isRulesSection = false
                } else {
                    val parts = line.split("|")
                    val page = parts[0].toInt()
                    val rule = parts[1].toInt()
                    val existing = rulesMap[page]
                    if (existing != null) {
                        existing.add(rule)
                        rulesMap[page] = existing
                    } else {
                        rulesMap[page] = mutableListOf(rule)
                    }
                }
            } else {
                val parts = line.split(",")
                val pages = mutableListOf<Int>()
                for (part in parts) {
                    pages.add(part.toInt())
                }
                var rightOrder = true
                for (i in parts.indices) {
                    val page = parts[i].toInt()
                    val rules = rulesMap[page]
                    if (rules != null) {
                        for (rule in rules) {
                            if (parts.contains(rule.toString())) {
                                if (i > parts.indexOf(rule.toString())) {
                                    rightOrder = false
                                }
                            }
                        }
                    }
                }
                if (!rightOrder) {
                    for (x in pages.indices) {
                        for (i in pages.indices) {
                            val page = pages[i]
                            val rules = rulesMap[page]
                            var ruleIndexes = mutableListOf<Int>()
                            if (rules != null) {
                                for (rule in rules) {
                                    ruleIndexes.add(pages.indexOf(rule))
                                }
                                ruleIndexes = ruleIndexes.filter { it > -1 }.toMutableList()
                                if (ruleIndexes.isNotEmpty()) {
                                    ruleIndexes.sort()
                                    val smallestIndexOfRule = ruleIndexes.first()
                                    if (i > smallestIndexOfRule) {
                                        pages.remove(page)
                                        pages.add(smallestIndexOfRule , page)
                                    }
                                }
                            }
                        }
                    }
                    total += pages[pages.size / 2]
                }
            }
        }
        return total.toString()
    }
}