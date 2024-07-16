package kdpbp2e.structural.decorator

// [문제]
// 영화 스타트렉 시리즈의 모든 선장과 함선들 등록하고 조회할 수 있는 Repository 클래스
// 선장을 검색하면 로그를 남기는 새로운 Repository가 필요하다면?
open class StarTrekRepository {
    private val starshipCaptains = mutableMapOf("USS Enterprise" to "Jean-Luc Picard")

    open fun getCaptain(starshipName: String): String {
        return starshipCaptains[starshipName] ?: "Unknown"
    }

    open fun set(starshipName: String, captainName: String) {
        starshipCaptains[starshipName] = captainName
    }
}

// 일단 상속을 통해 해결 할 수 있다
// [문제 2]
// 선장 추가시 이름을 검증하는 새로운 Repository가 필요하게 되었다.
class LoggingGetCaptainStarTrekRepository: StarTrekRepository() {
    override fun getCaptain(starshipName: String): String {
        println("Getting captain for $starshipName")
        return super.getCaptain(starshipName)
    }
}
// StarTrekRepository를 상속 받는 ValidatingAddCaptainStarTrekRepository를 추가하여 해결
// [문제 3]
// 이번엔 로그 + 선장 이름 검증 하는 새 Repository가 필요하다
// 이젠 Repo 이름이 LoggingGetValidatingAddCaptainStarTrekRepository가 되어야 할까?
class ValidatingAddCaptainStarTrekRepository: StarTrekRepository() {
    override fun set(starshipName: String, captainName: String) {
        if (captainName.length > 7) {
            throw RuntimeException("$captainName name is longer than 7 characters!")
        }
        super.set(starshipName, captainName)
    }
}
