package nl.arviwastaken.adventofcode.year2022

import nl.arviwastaken.adventofcode.Solution
import nl.arviwastaken.adventofcode.util.getDigits
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

fun functionFactory (operator: (ULong, ULong) -> ULong, isSame: Boolean, number: ULong): (old: ULong) -> ULong =
    if (isSame) {
        fun (old: ULong): ULong = operator(old, old)
    } else {
        fun (old: ULong): ULong = operator(old, number)
    }


fun toFunction(functionAsText: String): (old: ULong) -> ULong {
    val isSame = functionAsText.getDigits().isEmpty()
    var number = 0UL
     if (!isSame) {
         number = functionAsText.getDigits().toULong()
    }
    return if (functionAsText.contains("+")) {
        functionFactory(ULong::plus, isSame, number)
    } else {
        functionFactory(ULong::times, isSame, number)
    }
}

class Day11(input: MutableList<String>) : Solution(input) {
    override fun part1(input: MutableList<String>): String {
        val monkeysAsStrings = mutableListOf<List<String>>()
        val monkeyAsString = mutableListOf<String>()
        for (line in input) {
            if (line.isEmpty()) {
                monkeysAsStrings.add(monkeyAsString.toList())
                monkeyAsString.clear()
                continue
            }
            monkeyAsString.add(line)
        }
        monkeysAsStrings.add(monkeyAsString.toList())
        var monkeys = mutableListOf<Monkey>()
        for (m in monkeysAsStrings) {
            monkeys.add(
                Monkey(
                    m[0].getDigits().toInt(),
                    m[1].split(":")[1].split(", ").map { it.getDigits().toULong() }.toMutableList(),
                    toFunction(m[2]),
                    divisibleBy = m[3].getDigits().toUInt(),
                    throwToMonkeyIfTrue = m[4].getDigits().toInt(),
                    throwToMonkeyIfFalse = m[5].getDigits().toInt(),
                )
            )
        }

        for (r in 1..20) {
            for (monkey in monkeys) {
                for (i in 0..<monkey.items.size) {
                    val operatedItem = monkey.operateOnItem(monkey.getFirstItem()) / 3UL
                    monkeys[monkey.inspectItem(operatedItem)].addItem(operatedItem)
                }
            }
        }

        monkeys = monkeys.sortedBy { it.amountOfItemsInspected }.reversed().toMutableList()
        return (monkeys[0].amountOfItemsInspected * monkeys[1].amountOfItemsInspected).toString()
    }

    override fun part2(input: MutableList<String>): String {
        val monkeysAsStrings = mutableListOf<List<String>>()
        val monkeyAsString = mutableListOf<String>()
        for (line in input) {
            if (line.isEmpty()) {
                monkeysAsStrings.add(monkeyAsString.toList())
                monkeyAsString.clear()
                continue
            }
            monkeyAsString.add(line)
        }
        monkeysAsStrings.add(monkeyAsString.toList())
        var monkeys = mutableListOf<Monkey>()
        for (m in monkeysAsStrings) {
            monkeys.add(
                Monkey(
                    m[0].getDigits().toInt(),
                    m[1].split(":")[1].split(", ").map { it.getDigits().toULong() }.toMutableList(),
                    toFunction(m[2]),
                    divisibleBy = m[3].getDigits().toUInt(),
                    throwToMonkeyIfTrue = m[4].getDigits().toInt(),
                    throwToMonkeyIfFalse = m[5].getDigits().toInt(),
                )
            )
        }

        var common = 1UL
        for (div in monkeys.map { it.divisibleBy }) {
            common *= div
        }

        for (r in 1..10000) {
            for (monkey in monkeys) {
                for (i in 0..<monkey.items.size) {
                    val operatedItem = monkey.operateOnItem(monkey.getFirstItem()) % common
                    monkeys[monkey.inspectItem(operatedItem)].addItem(operatedItem)
                }
            }
        }
        monkeys = monkeys.sortedBy { it.amountOfItemsInspected }.reversed().toMutableList()
        return (monkeys[0].amountOfItemsInspected * monkeys[1].amountOfItemsInspected).toString()
    }
}

class Monkey(
    val monkeyNr: Int,
    val items: MutableList<ULong>,
    val operation: (old: ULong) -> ULong,
    val divisibleBy: UInt,
    private val throwToMonkeyIfTrue: Int,
    private val throwToMonkeyIfFalse: Int,
    ) {
    var amountOfItemsInspected = 0UL

    fun addItem(item: ULong) {
        this.items.add(item)
    }

    fun getFirstItem(): ULong {
        return items.removeFirst()
    }

    fun operateOnItem(item: ULong): ULong {
        return this.operation(item)
    }

    fun inspectItem(item: ULong): Int {
        amountOfItemsInspected += 1U

        return if (item.rem(divisibleBy) == 0UL) {
            throwToMonkeyIfTrue
        } else {
            throwToMonkeyIfFalse
        }
    }
}
