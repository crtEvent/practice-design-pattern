package kdpbp2e.concurrency.mutex

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

// 상호 배제 Mutual Exclusion = Mutex
// 여러 코루틴이 동시에 접근할 수 있는 공유 상태를 보호하기 위해 사용

fun main() {
    runBlocking {
        var counter = 0
        val mutex = Mutex()
        val jobs = List(10) {
            async(Dispatchers.Default) {
                repeat(1000) {
                    counter++ // ++는 원자적 연산이 아니기 때문에 결과는 10,000보다 작을 것이다
                }
            }
        }
        jobs.awaitAll()

        println(counter)
    }
}