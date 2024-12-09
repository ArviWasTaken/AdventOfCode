package nl.arviwastaken.adventofcode.year2024

import nl.arviwastaken.adventofcode.Solution
import nl.arviwastaken.adventofcode.util.retrieveExample
import nl.arviwastaken.adventofcode.util.retrieveInput

fun main() {
    val example = retrieveExample()
    println("Running examples for day")
    Day9(example).run()
    val input = retrieveInput()
    println("\nRunning inputs for day")
    Day9(input).run()
}

class Day9(input: MutableList<String>) : Solution(input) {
    override fun part1(input: MutableList<String>): String {
        val list = unPackDiskMap(input[0])
        var leftPointer = 0

        for (i in list.lastIndex downTo 0) {
            val space = list[i]
            if (i < leftPointer) break

            if (space.first != -1) {
                var dataSize = space.second
                var fileLocation = i
                while (dataSize != 0) {
                    val leftSpace = list[leftPointer]
                    if (leftSpace.first != -1) {
                        leftPointer++
                    } else {
                        val emptySize = leftSpace.second
                        if (leftPointer >= i) {
                            break
                        } else {
                            when {
                                emptySize > dataSize ->
                                {
                                    list.add(leftPointer, Pair(space.first, dataSize))
                                    list[leftPointer + 1] = Pair(-1, emptySize - dataSize)
                                    fileLocation++
                                    list[fileLocation] = Pair(-1, dataSize)
                                    dataSize = 0
                                }
                                emptySize < dataSize ->
                                {
                                    list[leftPointer] = Pair(space.first, emptySize)
                                    dataSize -= emptySize
                                    list[fileLocation] = Pair(space.first, dataSize)
                                    list.add(fileLocation + 1, Pair(-1, emptySize))
                                }
                                else ->
                                {
                                    list[leftPointer] = Pair(space.first, emptySize)
                                    list[fileLocation] = Pair(-1, dataSize)
                                    dataSize = 0
                                }
                            }
                        }
                    }
                }
            }
        }
        return calculateChecksum(list)
    }

    override fun part2(input: MutableList<String>): String {
        val list = unPackDiskMap(input[0])

        for (i in list.lastIndex downTo 0) {
            val space = list[i]
            var leftPointer = 0
            if (space.first != -1) {
                var dataSize = space.second
                while (dataSize != 0) {
                    val leftSpace = list[leftPointer]
                    if (leftSpace.first != -1) {
                        leftPointer++
                    } else {
                        if (leftPointer >= i) {
                            break
                        }
                        val emptySize = leftSpace.second
                        when {
                            emptySize > dataSize -> {
                                list.add(leftPointer, Pair(space.first, dataSize))
                                list[leftPointer + 1] = Pair(-1, emptySize - dataSize)
                                list[i + 1] = Pair(-1, dataSize)
                                dataSize = 0
                            }
                            emptySize == dataSize -> {
                                list[leftPointer] = Pair(space.first, emptySize)
                                list[i] = Pair(-1, dataSize)
                                dataSize = 0
                            }
                            else -> leftPointer++
                        }
                    }
                }
            }
        }
        return calculateChecksum(list)
    }

    private fun unPackDiskMap(line: String) : MutableList<Pair<Int, Int>> {
        val list = mutableListOf<Pair<Int, Int>>()
        var fileID = 0
        for (i in line.indices) {
            if (i % 2 == 0) {
                list.add(Pair(fileID, line[i].digitToInt()))
                fileID++
            } else {
                list.add(Pair(-1, line[i].digitToInt()))
            }
        }
        return list
    }

    private fun calculateChecksum(list: MutableList<Pair<Int, Int>>): String {
        var total = 0L
        var index = 0
        for (z in list.indices) {
            val block = list[z]
            if (block.first != -1) {
                for (i in 0..<block.second) {
                    total += block.first * index
                    index++
                }
            } else {
                index += block.second
            }
        }
        return total.toString()
    }
}