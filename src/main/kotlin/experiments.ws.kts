class Cafe {

    fun buyCoffee(cc: CreditCard, p: Payments): Coffee {
        val cup = Coffee(name = "Filterbruehe", price = 2.50)
        p.charge(cc, cup.price) // Seiteneffekt! ...testbar nur mit Mocks! wiederverwendbare Einheit? separation-of-concerns?
        return cup
    }
}

class Payments {

    fun charge(cc: CreditCard, price: Double) {
        println("charging $cc with $price")
        val fees = price * 2 / 100
        println("charging fees of $fees")
    }
}

data class CreditCard(val number: String)

data class Coffee(val name: String, val price: Double)

// ----------------------------------------------------------------------

val cafe = Cafe()
val creditCard = CreditCard("1234567890")
val payments = Payments()

val coffee = cafe.buyCoffee(creditCard, payments)

val coffees: List<Coffee> = List(5) { cafe.buyCoffee(creditCard, payments) } // nicht gut... 5 mal Gebuehren!
