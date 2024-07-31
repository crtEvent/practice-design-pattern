package kdpbp2e.concurrency.fanin

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

/* [Fan In]
 * 여러 일꾼의 작업 결과를 하나로 모으기 위한 패턴
 * 여러 일꾼이 결과를 생성한 뒤에 이를 하나로 모아야 할 때 사용
 */

fun main() {
    runBlocking {
        val workChannel = generateWork()
        val resultChannel = Channel<String>()
        val workers = List(10) {
            doWorkAsync(workChannel, resultChannel)
        }

        resultChannel.consumeEach {
            println(it)
        }
    }
}

fun CoroutineScope.generateWork() = produce {
    for (i in 1..10_000) {
        send("page$i")
    }
    close()
}

private fun CoroutineScope.doWorkAsync(
    channel: ReceiveChannel<String>,
    resultChannel: Channel<String>
) = async(Dispatchers.Default) {
    for (p in channel) {
        resultChannel.send(p.repeat(2))
    }
}