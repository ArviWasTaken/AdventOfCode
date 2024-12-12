package nl.arviwastaken.adventofcode.year2024

import nl.arviwastaken.adventofcode.Solution
import nl.arviwastaken.adventofcode.util.retrieveExample
import nl.arviwastaken.adventofcode.util.retrieveInput

fun main() {
    val example = retrieveExample()
    println("Running examples for day")
    Day12(example).run()
    val input = retrieveInput()
    println("\nRunning inputs for day")
    Day12(input).run()
}

class Day12(input: MutableList<String>) : Solution(input) {
    override fun part1(input: MutableList<String>): String {
        val grid = mutableListOf<MutableList<Char>>( )
        for (i in input.indices) {
            grid.add(input[i].toCharArray().toMutableList())
        }
        val visitedPlots = mutableSetOf<Pair<Int, Int>>()
        val areas = mutableListOf<Set<Pair<Int, Int>>>()
        for (y in grid.indices) {
            for (x in grid[0].indices) {
                val plot = Pair(x, y)
                if (visitedPlots.contains(plot)) {
                    continue
                }
                val area = findPlotNeighboursOfSameType(grid, visitedPlots, plot)
                areas.add(area)
                visitedPlots.addAll(area)
            }
        }
        var total = 0
        for (area in areas) {
            var amountFencing = 0
            for (plot in area) {
                amountFencing += amountOfFences(area, plot)
            }
            total += area.count() * amountFencing
        }
        return total.toString()
    }

    private fun amountOfFences(area: Set<Pair<Int, Int>>, plot: Pair<Int, Int>): Int {
        val xOffsets = intArrayOf(-1, 0, 1, 0)
        val yOffsets = intArrayOf(0, 1, 0, -1)
        var fenceCount = 4
        for (dir in 0..<4) {
            val checky = plot.second + yOffsets[dir]
            val checkx = plot.first + xOffsets[dir]
            if (area.contains(Pair(checkx, checky))) {
                fenceCount--
            }
        }
        return fenceCount
    }

    private fun findPlotNeighboursOfSameType(grid: MutableList<MutableList<Char>>, visitedPlots: MutableSet<Pair<Int, Int>>, plot: Pair<Int, Int>): Set<Pair<Int, Int>> {
        val xOffsets = intArrayOf(-1, 0, 1, 0)
        val yOffsets = intArrayOf(0, 1, 0, -1)

        val foundNeighbours = mutableListOf<Pair<Int, Int>>()
        for (dir in 0..<4) {
            val checky = plot.second + yOffsets[dir]
            val checkx = plot.first + xOffsets[dir]
            if (checkx < 0 || checky < 0 || checky >= grid.size || checkx >= grid[0].size ) {
                continue
            }
            if (grid[checky][checkx] == grid[plot.second][plot.first] && !visitedPlots.any{vp -> vp == Pair(checkx, checky) }) {
                foundNeighbours.add(Pair(checkx, checky))
            }
        }

        val foundPlots = mutableSetOf<Pair<Int, Int>>()
        visitedPlots.add(plot)
        foundPlots.add(plot)

        for (neighbour in foundNeighbours) {
            val area = findPlotNeighboursOfSameType(grid, visitedPlots, neighbour)
            foundPlots.addAll(area)
            visitedPlots.addAll(area)
        }
        return foundPlots
    }

    override fun part2(input: MutableList<String>): String {
       return ""
    }
}