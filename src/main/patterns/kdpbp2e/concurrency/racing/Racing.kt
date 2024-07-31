package kdpbp2e.concurrency.racing

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.selectUnbiased
import kotlin.random.Random

/* [Racing]
 * 여러 작업을 동시에 실행한 뒤 먼저 반환되는 '승자'의 결과만 사용하고 나머지 '패자'의 결과는 버리는 패턴
 */

fun main() {
    runBlocking {
        while (true) {
            val winner = selectUnbiased<Pair<String, String>> {
                preciseWeather().onReceive { preciseWeatherResult ->
                    preciseWeatherResult
                }
                weatherToday().onReceive { weatherTodayResult ->
                    weatherTodayResult
                }
            }
            println(winner)
            delay(1000)
        }
    }
}

fun CoroutineScope.preciseWeather() = produce {
    delay(Random.nextLong(100))
    send("Precise Weather" to "+25c")
}

fun CoroutineScope.weatherToday() = produce {
    delay(Random.nextLong(100))
    send("Weather Today" to "+24c")
}