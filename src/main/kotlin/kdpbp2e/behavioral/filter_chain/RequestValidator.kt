package kdpbp2e.behavioral.filter_chain

/* 책임 사슬 패턴
 * 복잡한 로직을 여러 개의 작은 단계로 쪼개는 방법!
 */
fun main() {
    val req = Request(
        "developer@company.com",
        "질문 있어요!"
    )

    val chain = BasicValidationHandler(
        KnownEmailHandler(
            JuniorDeveloperFilterHandler(
                AnswerHandler()
            )
        )
    )

    val res = chain.handle(req)

    println(res)
}


data class Request(val email:String, val question: String) {
    fun isKnownEmail(): Boolean {
        return true
    }

    fun isFromJuniorDeveloper(): Boolean {
        return true
    }
}

data class Response(val answer: String)

interface Handler {
    fun handle(request: Request): Response
}

class BasicValidationHandler(private val next: Handler): Handler {
    override fun handle(request: Request): Response {
        if (request.email.isEmpty() || request.question.isEmpty()) {
            throw IllegalArgumentException()
        }

        return next.handle(request)
    }
}

class KnownEmailHandler(private val next: Handler): Handler {
    override fun handle(request: Request): Response {
        if (!request.isKnownEmail()) {
            throw IllegalArgumentException()
        }

        return next.handle(request)
    }
}

class JuniorDeveloperFilterHandler(private val next: Handler): Handler {
    override fun handle(request: Request): Response {
        if (!request.isFromJuniorDeveloper()) {
            throw IllegalArgumentException()
        }

        return next.handle(request)
    }
}

class AnswerHandler(): Handler {
    override fun handle(request: Request): Response {
        return Response("최종 결과!")
    }
}