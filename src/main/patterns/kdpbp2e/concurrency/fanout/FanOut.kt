package kdpbp2e.concurrency.fanout

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce

/* [Fan Out]
 * 작업을 여러 동시성 프로세서로 배분하기 위한 패턴
 */

fun main() {
    runBlocking {
        val workChannel = generateWork()

        val workers = List(10) { id ->
            doWork(id, workChannel) // 여러 일꾼이 같은 채널을 읽게 한다
        }
    }
}

fun CoroutineScope.generateWork() = produce {
    for (i in 1..10_000) {
        send("page$i")
    }
    close()
}

private fun CoroutineScope.doWork(
    id: Int,
    channel: ReceiveChannel<String>
) = launch(Dispatchers.Default) {
    for (p in channel) {
        println("Worker $id processed $p")
    }
}