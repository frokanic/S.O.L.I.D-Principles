package liskov_substitution_principle

fun main() {

}


/** _ EXAMPLE */

/** Correct implementation
 *
 *  As we can see below, Duck and Eagle inherit from flying bird, where
 *  Penguin does not. This is intentional, since the bird described by
 *  the Penguin class cannot fly, and thus inheriting from Flyable would
 *  violate the open / close principle, since a subclass would not be able
 *  to do everything its superclass does.
 */

open class Bird {
    fun move() = println("I am moving")
}

interface Flyable {
    fun fly()
}

open class FlyingBird : Bird(), Flyable {
    override fun fly() = println("I am flying")
}

class Duck : FlyingBird()

class Eagle : FlyingBird()

class Penguin : Bird()


/** Incorrect implementation */

class PenguinIncorrect : Flyable {
    override fun fly() {
        throw UnsupportedOperationException("Penguins cannot fly!")
    }
}