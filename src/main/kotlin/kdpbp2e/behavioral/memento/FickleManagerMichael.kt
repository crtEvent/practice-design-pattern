package kdpbp2e.behavioral.memento

/* [문제 상황]
 * 매니저는 생각을 두 가지만 저장할 수 있다.
 * 사라지는 이전 상태를 어떻게 기억할 수 있을 까?
 *
 * [해결]
 *
 */
class Manger {
    private var thoughts = mutableListOf<String>()

    inner class Memory(private val mindState: List<String>) {
        fun restore() {
            thoughts = mindState.toMutableList()
        }
    }

    fun saveThatThought(): Memory {
        return Memory(thoughts.toList())
    }

    fun `내가 그때 무슨 생각을 하고 있었지?`(memory: Memory) {
        memory.restore()
    }

    fun think(thought: String) {
        thoughts.add(thought)
        if (thoughts.size > 2) { // 한번에 두 가지 생각만 할 수 있음...
            thoughts.removeFirst()
        }
    }

    fun printThoughts() {
        println(thoughts)
    }
}

fun main() {
    val michael = Manger()
    michael.think("1. A 해야 해")
    michael.think("2. 아.. 점심 뭐 먹지?")
    val memento = michael.saveThatThought()

    michael.think("3. 아.. 내일 점심 뭐 먹지?")
    michael.think("4. 아니, 내가 언제 A 하자고 했어? B 해야 해")
    michael.printThoughts()

    println("내가 그때 무슨 생각을 하고 있었지?")
    michael.`내가 그때 무슨 생각을 하고 있었지?`(memento)
    michael.printThoughts()
}