package kdpbp2e.behavioral.template_method

fun main() {
    runSchedule(
        beforeLunch = { println("월요일 점심 전엔?") },
        afterLunch = { println("월요일 점심 후엔?") },
        bossHook = { println("매니저가 한마디!") },
    )
}

fun runSchedule(
    beforeLunch: () -> Unit,
    afterLunch: () -> Unit,
    bossHook: (() -> Unit)? = fun() { println("??")},
) {

    fun arriveToWork() {
        println("사무실에 등장!")
    }

    val drinkCoffee = { println("커피 마심") }

    fun goToLunch() = println("점심 먹음")

    val goHome = fun() {
        println("퇴근")
    }

    arriveToWork()
    drinkCoffee()
    beforeLunch()
    goToLunch()
    afterLunch()
    bossHook?.let { it() }
    goHome()
}
