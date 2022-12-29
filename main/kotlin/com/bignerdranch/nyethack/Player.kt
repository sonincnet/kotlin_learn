package com.bignerdranch.nyethack
import java.io.File

class Player(_name: String,
             var healthPoints: Int,
             val isBlessed: Boolean,
             private val isImmortal: Boolean) {
    init {
        require(healthPoints > 0, { "healthPoints must be greater than zero." })
        require(_name.isNotBlank(), { "Player must have a name." })
    }
    constructor(name: String) : this(name,
        healthPoints = 100,
        isBlessed = true,
        isImmortal = false){
        if(name.toLowerCase() == "kar") healthPoints = 40
    }
    var name = _name
        get() = "${field.capitalize()} of $hometown"
            private set(value) {
            field = value.trim()
        }
    val hometown = delEscape(selectHometown())
    private fun selectHometown() = File("./main/resources/data/towns.txt")
        .readText()
        .split("\n")
        .random()

    //Crutch. When read town.txt, sity's name reading with \r char
    private fun delEscape(town: String): String{
        var _town = ""
        if(town.contains("\r")){
            for(i in 0 until  town.length-1){
                _town = _town.plus(town[i])
            }
        } else {
            return town
        }
        return _town
    }

    fun auraColor(isBlessed: Boolean): String {
        val auraVisible = isBlessed && healthPoints > 50 || isImmortal
        val auraColor = if (auraVisible) "GREEN" else "NONE"
        return auraColor
    }
    fun formatHealthStatus() = when (healthPoints) {
        100 -> "is in excellent condition!"
        in 90..99 -> "has a few scratches."
        in 75..89 -> if (isBlessed) {
            "has some minor wounds, but is healing quite quickly!"
        } else {
            "has some minor wounds."
        }

        in 15..74 -> "looks pretty hurt."
        else -> "is in awful condition!"
    }

    fun castFireball(numFireballs: Int = 2) =
        println("A glass of Fireball springs into existence. (x$numFireballs)")

    fun status(): String{
        val message = "(Aura: ${auraColor(isBlessed)}) " +
                "(Blessed: ${if (isBlessed) "YES" else "NO"})\n" +
                "$name ${formatHealthStatus()}"
        return message
    }

}
