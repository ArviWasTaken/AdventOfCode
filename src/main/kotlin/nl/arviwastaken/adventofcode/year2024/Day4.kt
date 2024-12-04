package nl.arviwastaken.adventofcode.year2024

import nl.arviwastaken.adventofcode.Solution
import nl.arviwastaken.adventofcode.util.retrieveExample
import nl.arviwastaken.adventofcode.util.retrieveInput

fun main() {
    val example = retrieveExample()
    println("Running examples for day")
    Day4(example).run()
    val input = retrieveInput()
    println("\nRunning inputs for day")
    Day4(input).run()
    }

class Day4(input: MutableList<String>) : Solution(input) {
    override fun part1(input: MutableList<String>): String {
        val grid = Array(input.count()) { CharArray(input[0].length)}
        for (i in input.indices) {
            grid[i] = input[i].toCharArray()
        }
        var total = 0
        for (y in grid.indices) {
            for (x in grid[0].indices) {
                total += findWord(grid, x, y, "XMAS")
            }
        }
        return total.toString()
    }

    fun findWord(grid: Array<CharArray>, x: Int, y: Int, word: String) : Int {
        if (grid[x][y] != word[0]) {
            return 0
        }
        val xOffsets = intArrayOf(-1, -1, -1, 0, 0, 1, 1, 1)
        val yOffsets = intArrayOf(-1, 0, 1, -1, 1, -1, 0, 1)

        var foundOccurences = 0

        for (dir in 0..<8) {
            var checky = y + yOffsets[dir]
            var checkx = x + xOffsets[dir]
            for (i in word.indices) {
                if (i == 0 || checkx >= grid[0].size || checky >= grid.size || checkx < 0 || checky < 0) {
                    continue
                }
                if (grid[checkx][checky] != word[i]) {
                    break
                }
                checkx += xOffsets[dir]
                checky += yOffsets[dir]
                if (i == word.lastIndex) {
                    foundOccurences++
                }
            }
        }
        return foundOccurences
    }

    override fun part2(input: MutableList<String>): String {
        val grid = Array(input.count()) { CharArray(input[0].length)}
        for (i in input.indices) {
            grid[i] = input[i].toCharArray()
        }
        var total = 0
        for (y in grid.indices) {
            for (x in grid[0].indices) {
                if (findX(grid, x, y))
                {
                    total++
                }
            }
        }
        return total.toString()
    }

    fun findX(grid: Array<CharArray>, x: Int, y: Int) : Boolean {
        if (grid[x][y] != 'A') {
            return false
        }
        if (x == 0 || y == 0 || x == grid.lastIndex || y == grid[0].lastIndex) {
            return false
        }
        val firstDiagnol = charArrayOf(grid[x-1][y-1], grid[x+1][y+1])
        val secondDiagnol = charArrayOf(grid[x-1][y+1], grid[x+1][y-1])

        return firstDiagnol.contains('M') && secondDiagnol.contains('M') && firstDiagnol.contains('S') && secondDiagnol.contains('S')
    }
}