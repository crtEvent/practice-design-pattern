package kdpbp2e.behavioral.command

/* 해결하고 싶은 문제
 * 객체의 동작들을 기억해 뒀다가 나중에 실행되도록 하 고 싶다
 * 예제는 Trooper의 이동 명령을 여러개 저장해서 원하는 루트로 이동할 수 있게 한다
 *
 * 함수 생성기를 만들어 인자가 다양항 명령을 처리할 수 있게 되었다
 */
typealias Command = () -> Unit
val moveGenerator = fun(trooper: Trooper, x: Int, y: Int): Command {
    return fun() {
        trooper.move(x, y)
    }
}
val attackGenerator = fun(trooper: Trooper, direction: String): Command {
    return fun() {
        trooper.attack(direction)
    }
}

class Trooper {
    private val commands = mutableListOf<Command>()

    fun move(x: Int, y: Int) {
        println("$x, $y 로 이동")
    }

    fun attack(direction: String) {
        println("$direction 방향으로 공격")
    }

    fun appendMove(x: Int, y: Int) = apply {
        commands.add(moveGenerator(this, x, y))
    }

    fun appendAttack(direction: String) = apply {
        commands.add(attackGenerator(this, direction))
    }

    fun execute() {
        while (commands.isNotEmpty()) {
            val command = commands.removeFirst()
            command()
        }
    }
}

fun main() {
    val trooper = Trooper()
    trooper
        .appendMove(20, 0)
        .appendMove(20, 20)
        .appendMove(5, 20)
        .appendAttack("LEFT")
        .execute()
}