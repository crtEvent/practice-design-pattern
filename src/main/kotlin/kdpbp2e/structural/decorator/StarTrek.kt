package kdpbp2e.structural.decorator

interface StarTrekRepository {
    fun getCaptain(starshipName: String): String
    fun setCaptain(starshipName: String, captainName: String)
}

class DefaultStarTrekRepository : StarTrekRepository {
    private val starshipCaptains = mutableMapOf("USS Enterprise" to "Jean-Luc Picard")

    override fun getCaptain(starshipName: String): String {
        return starshipCaptains[starshipName] ?: "Unknown"
    }

    override fun setCaptain(starshipName: String, captainName: String) {
        starshipCaptains[starshipName] = captainName
    }
}

class LoggingGetCaptain(
    private val repository: StarTrekRepository
) : StarTrekRepository by repository
{
    override fun getCaptain(starshipName: String): String {
        println("[LOG] Getting captain for $starshipName")
        return repository.getCaptain(starshipName)
    }
}

class ValidatingSetCaptain(
    private val repository: StarTrekRepository
) : StarTrekRepository by repository
{
    private val maxNameLength = 20

    override fun setCaptain(starshipName: String, captainName: String) {
        require(captainName.length < maxNameLength) {
            "$captainName name is longer than $maxNameLength characters!"
        }
        repository.setCaptain(starshipName, captainName)
    }
}

fun main() {
    val starTrekRepository = DefaultStarTrekRepository()
    val withValidating = ValidatingSetCaptain(starTrekRepository)
    val withLoggingAndValidating = LoggingGetCaptain(withValidating)

    withLoggingAndValidating.setCaptain("USS Voyager", "Kathryn Janeway")
    withLoggingAndValidating.getCaptain("USS Voyager")
}
