package kdpbp2e.behavioral.strategy

enum class Direction {
    LEFT, RIGHT
}

interface Weapon {
    fun shoot(x: Int, y: Int, direction: Direction): Projectile
}

class Arrow: Weapon {
    override fun shoot(x: Int, y: Int, direction: Direction) = Projectile(x, y, direction)
}

class Javelin: Weapon {
    override fun shoot(x: Int, y: Int, direction: Direction) = Projectile(x, y, direction)
}

data class Projectile( // 탄환
    private var x: Int,
    private var y: Int,
    private var direction: Direction,
)

class Hero {
    private var direction = Direction.LEFT
    private var x: Int = 42
    private var y: Int = 173
    private var currentWeapon: Weapon = Arrow()

    fun shoot(): Projectile {
        return currentWeapon.shoot(x, y, direction)
    }

    fun equip(weapon: Weapon) {
        currentWeapon = weapon // 전략 패턴: 객체의 동작은 런타임에 변경할 수 있게 하는 것
    }
}