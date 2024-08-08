package etc.enhanced_state

/* [상태 패턴의 문제점]
 * > 코드스피츠 스터디에 나온 코드 이해하기 https://www.youtube.com/@CodeSpitz
 *
 * 대부분 책의 상태 패턴 예제는 상태를 Context 객체에 State를 주입하는 메서드가 있다
 * 이렇게 되면 누구나 State를 변경할 수 있게 되므로 Context의 상태가 금방 망가진다
 *
 * State가 스스로 상태를 관리하게 하려면?
 *
 * 최초 상태 주입 외에는 상태 변화를 완전히 격리해서 상태 객체만 알게 해준다
 * 상태가 어떻게 변하는지는 Context에 전혀 없다
 *
 * 현재 상태        동작        결과 상태
 * ------------------------------------
 * 동전 없는 상태    동전 투입    동전 있는 상태
 * 동전 없는 상태    티켓 출력    동전 없는 상태
 * 동전 있는 상태    동전 투입    동전 있는 상태
 * 동전 있는 상태    티켓 출력    동전 없는 상태
 *
 */

abstract class State {
    var afterInsertCoin = this  // TicketMachine이 생성될 때 다시 설정됨
    var afterPrintTicket = this // TicketMachine이 생성될 때 다시 설정됨
    private var state = this

    protected abstract fun _insertCoin()
    fun insertCoin() {
        _insertCoin() // 템플릿 메소드 패턴
        state = afterInsertCoin
    }

    protected abstract fun _printTicket()
    fun printTicket() {
        _printTicket()
        state = afterPrintTicket
    }

    val currentState: State get()
            = if(state === this) this else state.currentState
}

class TicketMachine(private var state: State) {

    companion object {
        fun ticketPrinter() {}
        fun addCoinRepository() {}

        val coinState = object:State() {
            override fun _insertCoin() {
                println("이미 코인이 들어있습니다.")
            }

            override fun _printTicket() {
                println("티켓 출력.")
            }
        }

        val noCoinState = object:State() {
            override fun _insertCoin() {
                println("코인 추가.")
            }

            override fun _printTicket() {
                println("코인이 없습니다. 코인을 넣어주세요.")
            }
        }

        init {
            noCoinState.afterInsertCoin = coinState
            coinState.afterPrintTicket = noCoinState
        }
    }

    fun insetCoin() {
        state = state.currentState
        state.currentState.insertCoin()
    }

    fun printTicket() {
        state = state.currentState
        state.currentState.printTicket()
    }
}

fun main() {
    val machine = TicketMachine(TicketMachine.noCoinState)
    machine.printTicket()   // 코인이 없습니다. 코인을 넣어주세요.
    machine.insetCoin()     // 코인 추가.
    machine.insetCoin()     // 이미 코인이 들어있습니다.
    machine.printTicket()   // 티켓 출력.
}

/* `val machine = TicketMachine(TicketMachine.noCoinState)`으로 TicketMachine 생성 후 상태는?
 *
 * machine
 *   state (noCoinState$2)
 *     afterInsertCoin    (coinState$1)  // 여기는 어떻게 coinState가 나왔을까? TicketMachine 초기화 할때 다시 넣어줬음
 *     afterPrintTicket   (noCoinState$2)
 *     state              (noCoinState$2)
 *
 * noCoinState으로 TicketMachine을 초기화 했는데 afterInsertCoin이 coinState로 나옴
 * TicketMachine을 init 메서드에서 afterInsertCoin을 coinState로 삽입해 줬기 때문
 *
 *      init {
 *          noCoinState.afterInsertCoin = coinState
 *          coinState.afterPrintTicket = noCoinState
 *      }
 *
 * machine
 *   state (noCoinState$2)
 *     afterInsertCoin    (coinState$1)
 *       afterInsertCoin    (coinState$1)
 *       afterPrintTicket   (noCoinState$2)
 *       state              (coinState$1)
 *     afterPrintTicket   (noCoinState$2)
 *       afterInsertCoin    (coinState$1)
 *       afterPrintTicket   (noCoinState$2)
 *       state              (noCoinState$2)
 *     state              (noCoinState$2)
 *       afterInsertCoin    (coinState$1)
 *       afterPrintTicket   (noCoinState$2)
 *       state              (noCoinState$2)
 *  -> 위 상태가 재귀로 계속 들어가 있음
 */