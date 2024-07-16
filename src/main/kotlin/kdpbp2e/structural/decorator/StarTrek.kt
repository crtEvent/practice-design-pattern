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