package kdpbp2e.behavioral.observer

import kdpbp2e.behavioral.mediator.Me

/*
 * Cat에 있는 반복문을 합창단원들에게 위임하려고 한다 (왜?)
 * 그리고 Cat은 단원들에게 소리의 높낮이를 지시할 수 있게 하고 싶다
 *
 * 발행자를 설계할 때 여러 속성을 갖는 하나의 데이터 클래스를 사용하자!
 * 그래야 속성이 추가될 때 모든 단원들의 메서드 시그니처를 수정하지 않아도 된다
 */
fun main() {
    val catTheConductor = Cat()

    val bat = Bat()
    val turkey = Turkey()
    val dog = Dog()

    catTheConductor.joinChoir(bat::screech)
    catTheConductor.joinChoir(turkey::gobble)
    catTheConductor.joinChoir(dog::bark)
    catTheConductor.joinChoir(dog::howl)
    catTheConductor.conduct(3, SoundPitch.HIGH)
}

enum class SoundPitch {HIGH, LOW}
data class Message(val times: Int, val pitch: SoundPitch)

class Bat {
    fun screech(message: Message) {
        for (i in 1..message.times) {
            println("${message.pitch} - screech!!!!")
        }
    }
}


class Turkey {
    fun gobble(message: Message) {
        for (i in 1..message.times) {
            println("${message.pitch} - gobble! gobble!")
        }
    }
}


class Dog {
    fun bark(message: Message) {
        for (i in 1..message.times) {
            println("${message.pitch} - bark! bark!")
        }
    }

    fun howl(message: Message) {
        for (i in 1..message.times) {
            println("${message.pitch} - howl~")
        }
    }
}

typealias Call = (message: Message) -> Unit
class Cat {
    private val participants = mutableMapOf<Call, Call>()

    fun joinChoir(whatToCall: Call) {
        participants[whatToCall] = whatToCall
    }

    fun leaveChoir(whatNotToCall: Call) {
        participants.remove(whatNotToCall)
    }

    fun conduct(times: Int, pitch: SoundPitch) {
        for (participant in participants.values) {
            participant(Message(times = times, pitch = pitch))
        }
    }
}