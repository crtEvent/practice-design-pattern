package kdpbp2e.concurrency.scheduler

import kotlinx.coroutines.*
import java.util.concurrent.Executors
import java.util.concurrent.ForkJoinPool

/* [Scheduler]
 * 여러 작업의 실행을 추상화 한다
 * 실행 대상(무엇)과 방법(어떻게)을 분리하고, 실행에 소요되는 자원 사용을 최적화
 *
 * 코틀린의 dispatcher가 스케쥴러 패턴을 구현하고 있다
 *   - 코루틴(무엇)과 그것이 실행되는 스레드 풀(어떻게)를 분리함
 *
 */

fun main() {
    runBlocking {

        // 부모 코루틴의 분배기를 사용함
        launch {
            // Prints: main
            println(Thread.currentThread().name)
        }
        launch(Dispatchers.Default) {
            // Prints DefaultDispatcher-worker-1
            println(Thread.currentThread().name)
        }

        // 입출력 분배기 사용
        // 실행 시간이 오래 걸리거나 다른 코드의 실행을 막고 있을 법한 작업에 사용됨 ... 왜...
        // 최대 64개 사용 가능
        async(Dispatchers.IO) {
            for (i in 1..1000) {
                println(Thread.currentThread().name)
                yield()
            }
        }

        // 이건 뭐야? 책에 없잖아... 그냥 쓰레드 풀 만드는 코드야?
        val myDispatcher = Executors
            .newFixedThreadPool(4)
            .asCoroutineDispatcher()

        val forkJoinPool = ForkJoinPool(4).asCoroutineDispatcher()

        repeat(1000) {
            launch(forkJoinPool) {
                println(Thread.currentThread().name)
            }
        }
    }
}