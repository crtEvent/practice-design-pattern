package kdpbp2e.structural.bridge

// [문제]
// 상속 관계가 엄청 만은 상황에서 요구사항 번경이 생긴다면?
// 아래 Trooper interface에 메서드가 추가된다면
/* [문제]
 * 상속 관계가 엄청 만은 상황에서 요구사항 번경이 생긴다면?
 * 아래 Trooper interface에 메서드가 추가된다면
 * Trooper를 구현한 모든 객체가 컴파일 되지 않음
 */
interface Trooper {
    fun move(x: Long, y: Long)
    fun attackRebel(x: Long, y: Long)
    // fun shout(): String <- 요구사항 추가
}

open class StormTrooper : Trooper {
    override fun move(x: Long, y: Long) {
        println("보통 속도로 이동")
    }

    override fun attackRebel(x: Long, y: Long) {
        println("대부분 빗나감")
    }
}

open class ShockTrooper : Trooper {
    override fun move(x: Long, y: Long) {
        println("느린 속도로 이동")
    }

    override fun attackRebel(x: Long, y: Long) {
        println("명중률 높음")
    }
}

class RiotTrooper : StormTrooper() {
    override fun attackRebel(x: Long, y: Long) {
        println("전기 충격")
    }
}

class FlameTrooper : ShockTrooper() {
    override fun attackRebel(x: Long, y: Long) {
        println("화염 방사")
    }
}

class ScoutTrooper : ShockTrooper() {
    override fun move(x: Long, y: Long) {
        println("빠른 속도로 이동")
    }
}

fun main() {
    val riotTrooper = RiotTrooper();
    riotTrooper.attackRebel(2, 3)
}