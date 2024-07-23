package kdpbp2e.concurrency.barrier

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlin.random.Random

/* [Barrier]
 * 여러 비동기 계산을 한꺼번에 기다리는 방법
 * 프로그램을 잠시 멈추고 여러 개의 동시성 작업이 완료되기를 기다리게 한다
 * 여러 곳에서 자료를 가져올 때 주로 사용
 *
 */


/**
 * [문제 상황]
 * catchphrase를 수신하기 전까지 picture 데이터를 받아오지 못한다
 */
suspend fun fetchFavoriteCharacter(name: String) = coroutineScope {
    val catchphrase = getCatchphraseAsync(name).await()
    val picture = getPicture(name).await()
    FavoriteCharacter(name, catchphrase, picture)
}

data class FavoriteCharacter(
    val name: String,
    val catchphrase: String,
    val picture: ByteArray = Random.nextBytes(42),
)

fun CoroutineScope.getCatchphraseAsync(
    characterName: String
) = async {
    // Simulate network call
    delay(100)
    when (characterName) {
        "Microsoft" -> "Be What's Next"
        else -> "No catchphrase found"
    }
}

fun CoroutineScope.getPicture(
    characterName: String
) = async {
    // Simulate network call
    delay(500)
    when (characterName) {
        else -> Random.nextBytes(42)
    }
}