package kdpbp2e.behavioral.mediator

/* [문제점]
 * 1. 작업이 너무 많음.. 모든 이름을 기억하기가 힘들다
 * 2. 직원들이 어떻게 의사소통 하는지 다 알고 있어야 함
 * 3. George는 Kenny를 가지고 있고, kenny는 George를 가지고 있다. 양방향 의존관계임
 * 4. Kenny가 회사를 떠난다면? 변경 여파가 크다황
 *
 * [해결안]
 * 개체간 결합도를 낮추기 위해 중개인을 두자
 * 모든 객체는 다른 객체를 직접 아는 대신 중개인 하나만 알도록 하자
 */

interface ProductManager { // 중개인!
    fun work(): String
}

interface MarketingManager { // 중개인!
    fun work(isMajorRelease: Boolean): String
}

object Michael: ProductManager {
    private val qa1 = Kenny
    private val qa2 = Brad
    private val qa3 = George

    override fun work(): String {
        if (!qa1.isEating() && !qa1.isSleeping()) { // Kenny가 일할 수 있니?
            return qa1.test()
        } else if (!qa2.isEating() && !qa2.isSleeping()) { // Kenny가 바쁘면 Brad가 일할 수 있니?
            return qa2.test()
        } else if (!qa3.isWatchingTv()) { // Brad도 바쁘면 George가 일할 수 있니?
            return qa3.test()
        }
        return "아무도 일 안함"
    }
}

object Penny: MarketingManager {
    private val translator = Sandra

    override fun work(isMajorRelease: Boolean): String {
        if (isMajorRelease) { // 릴리즈가 있으면 일 시킴
           return translator.write()
        }
        return "아무도 일 안함"
    }
}

object MyCompany {
    private val manger = Michael

    fun taskComplete() {
        println(manger.work())
    }
}

object MyMind {
    private val manger = Penny

    fun taskComplete(isMajorRelease: Boolean) {
        println(manger.work(isMajorRelease))
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