package kdpbp2e.concurrency.sidekick_channel

import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.select

/* [Sidekick Channel]
 * 주 일꾼의 작업 일부를 조수 일꾼에게 넘길 수 있다
 *
 * 지속적인 스트림을 처리해야 하는데 처리기의 규모를 쉽게 늘릴 수 없을 때 사용
 */

fun main() {
    runBlocking {
        val batman = actor<String> {
            for (c in channel) {
                println("Batman이 ${c}을(를) 처리하고 있다.")
                delay(100)
            }
        }

        val robin = actor<String> {
            for (c in channel) {
                println("Robin이 ${c}을(를) 처리하고 있다.")
                delay(250)
            }
        }

        val epicFight = launch {
            for (villain in listOf("Jocker", "Bane", "Penguin", "Riddler", "Killer Croc")) {
                val result = select<Pair<String, String>> {
                    batman.onSend(villain) {
                        "Batman" to villain
                    }
                    robin.onSend(villain) {
                        "Robin" to villain
                    }
                }
                delay(90)
                println(result)
            }
        }

    }
}