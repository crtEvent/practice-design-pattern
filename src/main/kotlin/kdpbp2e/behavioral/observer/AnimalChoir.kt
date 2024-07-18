package kdpbp2e.behavioral.observer

fun main() {
    val catTheConductor = Cat()

    val bat = Bat()
    val turkey = Turkey()
    val dog = Dog()

    catTheConductor.joinChoir(bat::screech)
    catTheConductor.joinChoir(turkey::gobble)
    catTheConductor.joinChoir(dog::bark)
    catTheConductor.joinChoir(dog::howl)
    catTheConductor.conduct(3)
}

class Bat {
    fun screech() {
        println("screech!!!!")
    }
}


class Turkey {
    fun gobble() {
        println("gobble! gobble!")
    }
}


class Dog {
    fun bark() {
        println("bark! bark!")
    }

    fun howl() {
        println("howl~")
    }
}


class Cat {
    private val participants = mutableMapOf<() -> Unit, () -> Unit>()

    fun joinChoir(whatToCall: () -> Unit) {
        participants[whatToCall] = whatToCall
    }

    fun leaveChoir(whatNotToCall: () -> Unit) {
        participants.remove(whatNotToCall)
    }

    fun conduct(times: Int) {
        for (participant in participants.values) {
            for (i in 1..times) {
                participant()
            }
        }
    }
}