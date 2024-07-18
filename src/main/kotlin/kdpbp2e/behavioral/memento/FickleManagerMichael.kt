package kdpbp2e.behavioral.memento

/* [문제 상황]
 * 매니저는 생각을 두 가지만 저장할 수 있다.
 * 사라지는 이전 상태를 어떻게 기억할 수 있을 까?
 */
class Manger {
    private var thoughts = mutableListOf<String>()

    fun think(thought: String) {
        thoughts.add(thought)
        if (thoughts.size > 2) { // 한번에 두 가지 생각만 할 수 있음...
            thoughts.removeFirst()
        }
    }
}

fun main() {
    val manager = Manger()
    manager.think("A 해야 해")
    manager.think("아.. 점심 뭐 먹지?")
    manager.think("아.. 내일 점심 뭐 먹지?")
    manager.think("아니, 내가 언제 A 하자고 했어? B 해야 해")
}