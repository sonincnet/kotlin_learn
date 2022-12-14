package com.bignerdranch.nyethack
fun main(args: Array<String>) {

    runSimulation()
}

private fun runSimulation() {
    val greetingFunction = cGreetingFunction()
    println(greetingFunction("Guyal"))
}

private fun cGreetingFunction(): (String) -> String {
    val structureType = "hospitals"
    var numBuildings = 5
    return { playerName: String ->
        val currentYear = 2018
        numBuildings += 1
        println("Adding $numBuildings $structureType")
        "Welcome to SimVillage, $playerName! (copyright $currentYear)"
    }
}

//test 11.12.2022
