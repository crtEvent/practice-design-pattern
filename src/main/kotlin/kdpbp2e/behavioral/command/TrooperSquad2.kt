package kdpbp2e.behavioral.command

/* 해결하고 싶은 문제
 * 객체의 동작들을 기억해 뒀다가 나중에 실행되도록 하 고 싶다
 * 예제는 Trooper의 이동 명령을 여러개 저장해서 원하는 루트로 이동할 수 있게 한다
 */
interface Command {
    fun execute()
}

class Trooper {
    private val orders = mutableListOf<Command>()

    fun addOrder(order: Command) {
        this.orders.add(order)
    }

    fun executeOrders() {
        while (orders.isNotEmpty()) {
            val order = orders.removeFirst()
            order.execute()
        }
    }
}
