class Cafe {
    fun buyCoffee(cc: CreditCard): Pair<Coffee, Charge> {
        val cup = Coffee("Wiener Melange", 3.5)
        return Pair(cup, Charge(cc, cup.price))
    }

    fun buyCoffees(cc: CreditCard, n: Int): Pair<List<Coffee>, List<Charge>> {
        val purchases: List<Pair<Coffee, Charge>> = List(n) { buyCoffee(cc) }
        val (coffees, charges) = purchases.unzip()
        return Pair(coffees, charges)
    }
}

fun List<Charge>.coalesce(): List<Charge> =
    this.groupBy { it.cc }.values
        .map { it.reduce { a, b -> a.combine(b) } }

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
val creditCard1 = CreditCard("1111111111")
val creditCard2 = CreditCard("2222222222")

fun test() {
    val (coffees1, charges1) = cafe.buyCoffees(creditCard1, 3)
    println(charges1)
    val (coffees2, charges2) = cafe.buyCoffees(creditCard2, 2)
    println(charges2)

    val transactions = (coffees1 + coffees2).zip(charges1 + charges2)
    println(transactions)
    val (coffess, charges) = transactions.unzip()
    println(charges.coalesce())
}

fun main() {
    test()
}
