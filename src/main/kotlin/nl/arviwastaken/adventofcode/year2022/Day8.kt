package nl.arviwastaken.adventofcode.year2022

import nl.arviwastaken.adventofcode.Solution
import nl.arviwastaken.adventofcode.util.retrieveExample
import nl.arviwastaken.adventofcode.util.retrieveInput

fun main() {
    val example = retrieveExample()
    println("Running examples for day")
    Day8(example).run()
    val input = retrieveInput()
    println("\nRunning inputs for day")
    Day8(input).run()
    }


class Day8(input: MutableList<String>) : Solution(input) {
    override fun part1(input: MutableList<String>): String {
        val mostRightTreePosition = input[0].length -1
        val mostLowerTreePosition = input.size - 1
        var visibleTrees = 0
        for (y in 0..mostLowerTreePosition) {
            xLoop@ for (x in 0..mostRightTreePosition) {
                val currentTree = input[y][x].toString().toInt()
                if (x == mostRightTreePosition || x == 0 || y == mostLowerTreePosition || y == 0) {
                    visibleTrees++
                    continue@xLoop
                }
                for (leftTreePosition in x-1 downTo 0) {
                    val leftTree = input[y][leftTreePosition].toString().toInt()
                    if (leftTreePosition == 0 && leftTree < currentTree) {
                        visibleTrees++
                        continue@xLoop
                    }
                    if (leftTree >= currentTree) {
                        break
                    }
                }
                for (rightTreePosition in x+1..mostRightTreePosition) {
                    val rightTree = input[y][rightTreePosition].toString().toInt()
                    if (rightTreePosition == mostRightTreePosition && rightTree < currentTree) {
                        visibleTrees++
                        continue@xLoop
                    }
                    if (rightTree >= currentTree) {
                        break
                    }
                }
                for (topTreePosition in y-1 downTo 0) {
                    val topTree = input[topTreePosition][x].toString().toInt()
                    if (topTreePosition == 0 && topTree < currentTree) {
                        visibleTrees++
                        continue@xLoop
                    }
                    if (topTree >= currentTree) {
                        break
                    }
                }
                for (bottomTreePosition in y+1..mostLowerTreePosition) {
                    val bottomTree = input[bottomTreePosition][x].toString().toInt()
                    if (bottomTreePosition == mostLowerTreePosition && bottomTree < currentTree) {
                        visibleTrees++
                        continue@xLoop
                    }
                    if (bottomTree >= currentTree) {
                        break
                    }
                }
            }
        }
        return visibleTrees.toString()
    }

    override fun part2(input: MutableList<String>): String {
        val mostRightTreePosition = input[0].length -1
        val mostLowerTreePosition = input.size - 1
        var highestScenicScore = 0
        for (y in 1..<mostLowerTreePosition) {
            for (x in 1..<mostRightTreePosition) {
                val currentTree = input[y][x].toString().toInt()

                var leftDistance = 0
                for (leftTreePosition in x-1 downTo 0) {
                    val leftTree = input[y][leftTreePosition].toString().toInt()
                    leftDistance++
                    if (leftTree >= currentTree) {
                        break
                    }
                }
                var rightDistance = 0
                for (rightTreePosition in x+1..mostRightTreePosition) {
                    val rightTree = input[y][rightTreePosition].toString().toInt()
                    rightDistance++
                    if (rightTree >= currentTree) {
                        break
                    }
                }
                var topDistance = 0
                for (topTreePosition in y-1 downTo 0) {
                    val topTree = input[topTreePosition][x].toString().toInt()
                    topDistance++
                    if (topTree >= currentTree) {
                        break
                    }
                }
                var bottomDistance = 0
                for (bottomTreePosition in y+1..mostLowerTreePosition) {
                    val bottomTree = input[bottomTreePosition][x].toString().toInt()
                    bottomDistance++
                    if (bottomTree >= currentTree) {
                        break
                    }
                }
                val scenicScore = leftDistance * rightDistance * topDistance * bottomDistance
                if (highestScenicScore < scenicScore) {
                    highestScenicScore = scenicScore
                }
            }
        }
        return highestScenicScore.toString()
    }

}