package nl.arviwastaken.adventofcode.year2022

import nl.arviwastaken.adventofcode.Solution
import nl.arviwastaken.adventofcode.util.retrieveExample
import nl.arviwastaken.adventofcode.util.retrieveInput

fun main() {
    val example = retrieveExample()
    println("Running examples for day")
    Day7(example).run()
    val input = retrieveInput()
    println("\nRunning inputs for day")
    Day7(input).run()
}

class Day7(input: MutableList<String>): Solution(input){
    override fun part1(input: MutableList<String>): String {
        val iterator = input.iterator()
        iterator.next()
        val root = Directory("/", null)
        var current = root
        var line  = iterator.next()
        while (iterator.hasNext()){
            val parts = line.split(" ").map { it.removePrefix(" ") }

            if(parts[0] == "$") {
                if (parts[1] == "cd") {
                    current = if (parts[2] == "/") {
                        root
                    } else if (parts[2] == "..") {
                        current.parent!!
                    } else {
                        current.getChildDirectory(parts[2])
                    }
                    line = iterator.next()
                } else {
                    var listing = true
                    while (listing && iterator.hasNext()) {
                        val tmpLine = iterator.next()
                        val tmpParts = tmpLine.split(" ").map { it.removePrefix(" ") }
                        if(tmpParts[0] == "$") {
                            line = tmpLine
                            listing = false
                        } else {
                            if (tmpParts[0] == "dir") {
                                current.addDirectory(tmpParts[1])
                            } else {
                                current.addFile(tmpParts[1], tmpParts[0])
                            }
                        }
                    }
                }
            }
        }

        val list = mutableListOf<Int>()

        return root.getAllDirectoriesSizes(list).filter { it <= 100000 }.sum().toString()
    }

    override fun part2(input: MutableList<String>): String {
        val iterator = input.iterator()
        iterator.next()
        val root = Directory("/", null)
        var current = root
        var line  = iterator.next()
        while (iterator.hasNext()){
            val parts = line.split(" ").map { it.removePrefix(" ") }

            if(parts[0] == "$") {
                if (parts[1] == "cd") {
                    current = if (parts[2] == "/") {
                        root
                    } else if (parts[2] == "..") {
                        current.parent!!
                    } else {
                        current.getChildDirectory(parts[2])
                    }
                    line = iterator.next()
                } else {
                    var listing = true
                    while (listing && iterator.hasNext()) {
                        val tmpLine = iterator.next()
                        val tmpParts = tmpLine.split(" ").map { it.removePrefix(" ") }
                        if(tmpParts[0] == "$") {
                            line = tmpLine
                            listing = false
                        } else {
                            if (tmpParts[0] == "dir") {
                                current.addDirectory(tmpParts[1])
                            } else {
                                current.addFile(tmpParts[1], tmpParts[0])
                            }
                        }
                    }
                }
            }
        }

        root.print()

        val list = mutableListOf<Int>()
        return root.getAllDirectoriesSizes(list).filter { it > 30000000 - (70000000 - root.calculateSize()) }.min().toString()
    }
}

private class Directory(val name: String, val parent: Directory?) {
    var directories: MutableList<Directory>  = mutableListOf()
    var files: MutableList<Pair<String, String>>  = mutableListOf()

    fun addFile(name: String, size: String) {
        files.add(Pair(name, size))
    }
    fun addDirectory(name: String) {
        directories.add(Directory(name, this))
    }
    fun getChildDirectory(name: String) : Directory {
        return directories.first { it.name == name }
    }
    fun calculateSize(): Int {
        var totalSize = 0
        files.forEach { totalSize += it.second.toInt() }
        directories.forEach { totalSize += it.calculateSize() }
        return totalSize
    }
    fun getAllDirectoriesSizes(list: MutableList<Int>): List<Int> {
        list.add(calculateSize())
        directories.forEach { it.getAllDirectoriesSizes(list) }
        return list
    }

    // logging
    fun print(spacing: Int = 0) {
        var space = spacing(spacing)
        println("$space- $name")
        space += spacing(2)
        files.forEach { println("$space- ${it.first} ${it.second}") }
        directories.forEach { it.print(spacing + 2) }
    }

    private fun spacing(spacing: Int): String {
        var string = ""
        for (i in 0..<spacing) {
            string += " "
        }
        return string
    }
}