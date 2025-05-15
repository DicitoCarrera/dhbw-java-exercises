package dhbw

import java.util.Optional

fun main() {

    val animals = listOf(Fish("Goldfish", 0.3f, 0.2f), Bird("Eagle", 0.8f, 5.0f))

    animals.forEach { println("${it.description} says: ${it.move()}") }

    val zoo = Zoo(name = "Diegos Zoo", animals = animals)

    zoo.printAnimalsInfo()
}

data class Zoo(val name: String, val animals: List<Animal>)

fun Zoo.addAnimal(animal: Animal): Zoo {
    return this.copy(animals = this.animals + animal)
}

fun Zoo.getBiggestAnimalWeight(): Optional<Float> {
    val biggest = animals.maxByOrNull { it.weigthInKg }
    return if (biggest != null) Optional.of(biggest.weigthInKg) else Optional.empty()
}

fun Zoo.printAnimalsInfo() {
    animals.forEach { println("${it.description}: ${it.sizeInM}m, ${it.weigthInKg}kg") }
}

sealed class Animal(val description: String, val sizeInM: Float, val weigthInKg: Float)

data class Fish(val des: String, val size: Float, val weight: Float) : Animal(des, size, weight)

data class Bird(val des: String, val size: Float, val weight: Float) : Animal(des, size, weight)

fun Animal.move(): String =
        when (this) {
            is Fish -> "schwimm, schwimm... "
            is Bird -> "flatter, flatter... "
        }
