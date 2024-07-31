package kdpbp2e.behavioral.strategy

enum class Direction {
    LEFT, RIGHT
}

object Weapons {
    fun arrow(x: Int, y: Int, direction: Direction): Projectile {
        println("화살 공격")
        return Projectile(x, y, direction)
    }

    fun javelin(x: Int, y: Int, direction: Direction): Projectile {
        println("투창 공격")
        return Projectile(x, y, direction)
    }
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
    var currentWeapon = Weapons::arrow

    var shoot = fun() {
        currentWeapon(x, y, direction)
    }
}

fun main() {
    val hero = Hero()
    hero.shoot()
    hero.currentWeapon = Weapons::javelin
    hero.shoot()
}