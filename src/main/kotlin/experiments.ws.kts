class Cafe {
    fun buyCoffee(cc: CreditCard): Pair<Coffee, Charge> {
        val cup = Coffee("Wiener Melange", 3.5)
        return Pair(cup, Charge(cc, cup.price))
    }

    fun buyCoffees(cc: CreditCard, n: Int): Pair<List<Coffee>, Charge> {
        val purchases: List<Pair<Coffee, Charge>> = List(n) { buyCoffee(cc) }
        val (coffees, charges) = purchases.unzip()
        return Pair(coffees, charges.reduce { c1, c2 -> c1.combine(c2) })
    }
}

data class Charge(val cc: CreditCard, val amount: Double) {

    fun combine(other: Charge): Charge =
        if (cc == other.cc)
            Charge(cc, amount + other.amount)
        else throw Exception("Cannot combine charges to different cards")

}

class Payments {

    fun charge(charge: Charge) {
        TODO("charge with 1 fee only!")
    }
}

data class CreditCard(val number: String)

data class Coffee(val name: String, val price: Double)

// ----------------------------------------------------------------------

val cafe = Cafe()
val creditCard = CreditCard("1234567890")

fun test() {
    val (coffees, charge) = cafe.buyCoffees(creditCard, 3)
    println("$charge")
}

test()
