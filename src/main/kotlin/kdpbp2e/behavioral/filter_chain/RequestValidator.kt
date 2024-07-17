package kdpbp2e.behavioral.filter_chain

data class Request(val email:String, val question: String) {
    fun isKnownEmail(): Boolean {
        return true
    }

    fun isFromJuniorDeveloper(): Boolean {
        return false
    }
}

fun handleRequest(r: Request) {
    // Validate 유효성 검사
    if (r.email.isEmpty() || r.question.isEmpty()) {
        return
    }

    // Authenticate 인증
    if (r.isKnownEmail()) {
        return
    }

    // Authorize 인가
    if (r.isFromJuniorDeveloper()) {
        return
    }

    println("몰라요~")
}