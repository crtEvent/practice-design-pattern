package kdpbp2e.concurrency.deferred_value

import kotlinx.coroutines.*
import java.lang.RuntimeException
import kotlin.random.Random

/* [Deferred Value]
 * 비동기 계산 로직이 결과를 직접 반환하는 대신 결과값을 가리키는 참조를 반환한다.
 * Java의 Future, Javascript의 Promise
 *
 * 왜 이렇게 하는지?
 *   - 비동기 작업이 완료될 때까지 연산이 지연
 *   - 결과값이 아직 준비되지 않았기 때문에, 결과값 대신 결과를 나중에 가져올 수 있는 참조를 반환
 *   - 비동기 작업의 상태(예: 진행 중, 완료됨, 실패함)를 관리할 수 있음
 *
 * Deferred는 프록시, 상태 패턴도 구현하고 있다
 * ??? 어디에 ???)
 *
 */

/**
 * 50%의 확률로 Ok라는 문자열을 반환하고 다른 50%의 확률로 RuntimeException 예외 발생시킴
 */
suspend fun valueAsync(): Deferred<String> = coroutineScope {
    val deferred = CompletableDeferred<String>()
    launch {
        delay(100)
        if (Random.nextBoolean()) {
            deferred.complete("OK")
        } else {
            deferred.completeExceptionally(
                RuntimeException()
            )
        }
    }
    deferred
}

fun main() {
    runBlocking {
        val value = valueAsync()
        println(value.await())
    }
}