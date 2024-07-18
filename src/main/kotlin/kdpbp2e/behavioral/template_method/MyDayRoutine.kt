package kdpbp2e.behavioral.template_method

fun main() {
    val mondaySchedule = MondaySchedule()
    mondaySchedule.runSchedule()
}

abstract class DayRoutine {
    abstract fun doBeforeLunch()
    abstract fun doAfterLunch()

    fun runSchedule() {
        arriveToWork()
        drinkCoffee()
        doBeforeLunch()
        goToLunch()
        doAfterLunch()
        goHome()
    }

    open fun bossHook() {
        println("??")
    }

    private fun arriveToWork() {
        println("사무실에 등장!")
    }

    private fun drinkCoffee() {
        println("커피 마심")
    }

    private fun goToLunch() {
        println("점심 먹음")
    }

    private fun goHome() {
        println("퇴근")
    }
}

class MondaySchedule : DayRoutine() {
    override fun doBeforeLunch() {
        println("월요일 점심 전엔?")
    }

    override fun doAfterLunch() {
        println("월요일 점심 후엔?")
    }

    override fun bossHook() {
        println("월요일???")
    }
}