package com.bignerdranch.test
import java.io.File

import com.bignerdranch.nyethack.Player

fun main() {
    fun selectHometown() = File("./main/resources/data/towns.txt")
        .readText()
        .split("\n")
        .random()

    selectHometown()
}