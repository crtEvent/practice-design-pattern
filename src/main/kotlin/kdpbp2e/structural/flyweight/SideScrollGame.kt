package kdpbp2e.structural.flyweight

import java.io.File

enum class Direction {
    LEFT, RIGHT
}

/*
 * [문제]
 * 이미지의 용량이 크다면 TanzanianSnail 객체를 많이 만들기 부담스럽다
 */
class TanzanianSnail {
    val directionFacing = Direction.LEFT
    val sprites = List(8) { i ->
        File(when(i) {
            0 -> "snail-left.png"   // 왼쪽을 향하고 있는 이미지
            1 -> "snail-right.png"  // 오른쪽을 향하고 있는 이미지
            in 2..4 -> "snail-move-left-${i-1}.png" // 왼쪽으로 움직이는 이미지
            else -> "snail-move-right-${4-i}.png"        // 오른쪽으로 움직이는 이미지
        })
    }

    fun getCurrentSprite(): File {
        return when (directionFacing) {
            Direction.LEFT -> sprites[0]
            Direction.RIGHT -> sprites[1]
        }
    }
}