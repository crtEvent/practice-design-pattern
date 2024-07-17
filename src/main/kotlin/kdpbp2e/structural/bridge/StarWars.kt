package kdpbp2e.structural.bridge

/* [문제]
 * 상속 관계가 엄청 만은 상황에서 요구사항 번경이 생긴다면?
 * 아래 Trooper interface에 메서드가 추가된다면
 * Trooper를 구현한 모든 객체가 컴파일 되지 않음
 *
 * -------------------------------------
 *
 * 문제 원인: 서로 무관한 두가지 독립적인 속성(PointOfDamage, Meters)을 사용하고 있기 때문에 복잡한 클래스 계층이 생김
 * 해결 방법: 두 속성을 생성자 매개 변수로 전달할 수 있도록 변경하기
 *
 * 이제 Trooper에 새로운 메서드가 추가되어도 StormTrooper 한 곳만 고치면 된다
 *
 * 브리지 패턴은 의존성 주입과 함께 사용되는 경우가 많다
 * 예) DB를 사용하는 구현체를 쉽게 Mock으로 구현 가능 -> 테스트 용이
 *
 */
interface Trooper {
    fun move(x: Long, y: Long)
    fun attackRebel(x: Long, y: Long)
    // fun shout(): String <- 요구사항 추가
}

interface Weapon {
    fun attack(x: Long, y: Long): PointOfDamage
}
interface Legs {
    fun move(x: Long, y: Long): Meters
}
typealias PointOfDamage = Long
typealias Meters = Int

const val REGULAR_DAMAGE: PointOfDamage = 3L
const val REGULAR_SPEED: Meters = 1

class Rifle : Weapon {
    override fun attack(x: Long, y: Long) = REGULAR_DAMAGE
}
class Flamethrower : Weapon {
    override fun attack(x: Long, y: Long) = REGULAR_DAMAGE * 2
}
class ElectricShock : Weapon {
    override fun attack(x: Long, y: Long) = REGULAR_DAMAGE * 3
}

class RegularLegs : Legs {
    override fun move(x: Long, y: Long) = REGULAR_SPEED
}
class AthleticLegs : Legs {
    override fun move(x: Long, y: Long) = REGULAR_SPEED * 2
}

data class StormTrooper(
    private val weapon: Weapon,
    private val legs: Legs,
) : Trooper {
    override fun move(x: Long, y: Long) {
        println("보통 속도로 이동")
        legs.move(x, y)
    }

    override fun attackRebel(x: Long, y: Long) {
        println("대부분 빗나감")
        weapon.attack(x, y)
    }
}

fun main() {
    val stormTrooper = StormTrooper(Rifle(), RegularLegs());
    val flameTrooper = StormTrooper(Flamethrower(), RegularLegs());
    val scoutTrooper = StormTrooper(Rifle(), AthleticLegs());
}