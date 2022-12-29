package com.bignerdranch.nyethack

fun main() {
    val player = Player("Jonny")
    player.castFireball()
    // Состояние игрока

    player.status().run(::println)
//    player.printVal()

//  println("Hello")

}