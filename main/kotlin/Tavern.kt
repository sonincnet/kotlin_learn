import kotlin.math.*
import java.io.File

const val TAVERN_NAME = "Taernyl's Folly"
var playerDracoin: Double = 50.0
var playerGold: Int = (playerDracoin*1.43).toInt()
var playerSilver: Int = round(((playerDracoin*1.43) % 1 * 100)).toInt()

val patronList = mutableListOf("Eli", "Mordoc", "Sophie")
val lastName = listOf("Ironfoot", "Fernsworth", "Baggins")
val uniquePatrons = mutableSetOf<String>()
val menuList = File("./src/main/data/tavern-menu-items.txt")
    .readText()
    .split("\n")

fun main() {
//    println(patronList)
//    patronList.remove("Eli")
//    patronList.add("Alex")
//    patronList.add(0, "Alex")
//    patronList[0] = "Alexis"
//    println(patronList)
    if (patronList.contains("Eli")) {
        println("The tavern master says: Eli's in the back playing cards.")
    } else {
        println("The tavern master says: Eli isn't here.")
    }
    if (patronList.containsAll(listOf("Sophie", "Mordoc"))) {
        println("The tavern master says: Yea, they're seated by the stew kettle.")
    } else {
        println("The tavern master says: Nay, they departed hours ago.")
    }
    (0..9).forEach {
        val first = patronList.shuffled().first()
        val last = lastName.random()
        val name = "$first $last"
        uniquePatrons += name
    }
    println(uniquePatrons)

//    patronList.forEachIndexed { index, patron ->
//        println("Good evening, $patron - you're #${index + 1} in line.")
//        placeOrder(patron, menuList.random())
//    }
//    menuList.forEachIndexed { index, data ->
//        println("$index : $data")
//    }
}

private fun displayBalance() {
    println("Player's purse balance: Gold: $playerGold , Silver: $playerSilver")
    println("Player's purse balance Dracoin: ${"%.4f".format(playerDracoin)}")
}

fun performPurchase(price: Double): Boolean {
    displayBalance()
    val totalPurse: Double = playerGold + playerSilver / 100.0
    val totalPurseDracoin = playerDracoin
    if (totalPurse<price){
        println("Bartender: Sorry, you haven't money...")
        return false
    }
    println("Total purse: $totalPurse")
    println("Total purse Dracoin: $totalPurseDracoin")
    println("Purchasing item for $price")
    val remainingBalance = totalPurse - price
    val remainingBalanceDracoin  = remainingBalance/1.43
    println("Remaining balance: ${"%.2f".format(remainingBalance)}")
    println("Remaining balance Dracoin: ${"%.4f".format(remainingBalanceDracoin)}")
    val remainingGold = remainingBalance
    val remainingSilver = (remainingBalance % 1 * 100).roundToInt()
    playerDracoin = remainingBalanceDracoin
    playerGold = remainingGold.toInt()
    playerSilver = remainingSilver
    displayBalance()
    return true
}

private fun placeOrder(patronName: String, menuData: String) {
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)

    val (type, name, price) = menuData.split(',')
    val message_1 = "$patronName speaks with $tavernMaster about their order."
    val message_2 = "$patronName buys a $name} ($type}) for $price}"
    val phrase = if (name == "Dragon's Breath") {
        "$patronName exclaims ${toDragonSpeak("Ah, delicious $name}!")}"
    } else {
        "$patronName says: Thanks for the $name}."
    }
    println(message_1)
    println(message_2)
    if(performPurchase(price.toDouble())){
        println(phrase)
    } else {
        println("Madrigal: Damn shit!!!")
    }
    println("_______________________________________________")
}

private fun toDragonSpeak(phrase: String) =
    phrase.replace(Regex("[aeiou]")) {
        when (it.value) {
            "a" -> "4"
            "e" -> "3"
            "i" -> "1"
            "o" -> "0"
            "u" -> "|_|"
            else -> it.value
        }
    }