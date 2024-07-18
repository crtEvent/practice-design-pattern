package kdpbp2e.behavioral.mediator

/* [문제점]
 * 1. 작업이 너무 많음.. 모든 이름을 기억하기가 힘들다
 * 2. 직원들이 어떻게 의사소통 하는지 다 알고 있어야 함
 * 3. George는 Kenny를 가지고 있고, kenny는 George를 가지고 있다. 양방향 의존관계임
 * 4. Kenny가 회사를 떠난다면? 변경 여파가 크다
 */
object MyCompany {
    private val cto = Me
    private val qa1 = Kenny
    private val qa2 = Brad
    private val qa3 = George

    fun taskComplete() {
        if (!qa1.isEating() && !qa1.isSleeping()) { // Kenny가 일할 수 있니?
            println(qa1.test())
        } else if (!qa2.isEating() && !qa2.isSleeping()) { // Kenny가 바쁘면 Brad가 일할 수 있니?
            println(qa2.test())
        } else if (!qa3.isWatchingTv()) { // Brad도 바쁘면 George가 일할 수 있니?
            println(qa3.test())
        }
    }
}

object MyMind {
    private val translator = Sandra

    fun taskComplete(isMajorRelease: Boolean) {
        if (isMajorRelease) { // 릴리즈가 있으면 일 시킴
            println(translator.write())
        }
    }
}

object Me

interface QA {
    fun test(): String
}

interface Copywriter {
    fun write(): String
}

interface Parrot {
    fun isEating(): Boolean
    fun isSleeping(): Boolean
}

interface Owl {
    fun isWatchingTv(): Boolean
}

interface Kiwi {
    fun isWatchingTv(): Boolean
}

object Kenny: QA, Parrot {
    val developer = Me
    val peer = George

    override fun test(): String  {
        return "Kenny 일했어!"
    }

    override fun isEating(): Boolean {
        return false
    }

    override fun isSleeping(): Boolean {
        return false
    }
}

object Brad: QA, Parrot {
    val senior = Kenny
    val developer = Me

    override fun test(): String  {
        return "Brad 일했어!"
    }

    override fun isEating(): Boolean {
        return false
    }

    override fun isSleeping(): Boolean {
        return false
    }
}

object George: QA, Owl {
    val mate = Kenny
    val developer = Me

    override fun test(): String  {
        return "George 일했어!"
    }

    override fun isWatchingTv(): Boolean {
        return false
    }
}

object Sandra : Copywriter, Kiwi {
    override fun write(): String {
        return "Sandra 작업 완료"
    }

    override fun isWatchingTv(): Boolean {
        return false;
    }
}