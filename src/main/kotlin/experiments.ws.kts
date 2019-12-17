class Cafe {
    fun buyCoffee(cc: CreditCard): Pair<Coffee, Charge> {
        val cup = Coffee("Wiener Melange", 3.5)
        return Pair(cup, Charge(cc, cup.price))
    }
}

class Payments {

    fun charge(charge: Charge) {
        TODO("charge!")
    }
}

data class Charge(val cc: CreditCard, val amount: Double) {

    fun combine(other: Charge): Charge =
        if (cc == other.cc)
            Charge(cc, amount + other.amount)
        else throw Exception("Cannot combine charges to different cards")
}

data class CreditCard(val number: String)

data class Coffee(val name: String, val price: Double)

// ----------------------------------------------------------------------

val cafe = Cafe()
val creditCard = CreditCard("1234567890")

fun test() {
    val (coffee, charge) = cafe.buyCoffee(creditCard)
    println("$coffee $charge")
}

test()
