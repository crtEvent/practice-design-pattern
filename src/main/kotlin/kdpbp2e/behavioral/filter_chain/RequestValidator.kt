package kdpbp2e.behavioral.filter_chain

/* 책임 사슬 패턴
 * 복잡한 로직을 여러 개의 작은 단계로 쪼개는 방법!
 *
 * 코틀린에선 함수를 사용해서 코드를 더 간결하게 할 수 있음(그렇게 많이 간결해 진것도 아닌데?)
 * 라이브러리나 프레임 워크 처럼 다른 개발자가 기능을 확장할 가능성이 높다면 함수보다는 인터페이스를 사용하는게 더 명시적임
 */
fun main() {
    val req = Request(
        "developer@company.com",
        "질문 있어요!"
    )

    val chain = basicValidation(
        authentication(
            juniorValidation(
                finalResponse()
            )
        )
    )

    val res = chain(req)

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

typealias Handler = (request:Request) -> Response

val basicValidation = fun(next: Handler) =
    fun(request: Request): Response {
        if (request.email.isEmpty() || request.question.isEmpty()) {
            throw IllegalArgumentException()
        }
        return next(request)
    }

val authentication = fun(next: Handler) =
    fun(request: Request): Response {
        if (!request.isKnownEmail()) {
            throw IllegalArgumentException()
        }

        return next(request)
    }

val juniorValidation = fun(next: Handler) =
    fun(request: Request): Response {
        if (!request.isFromJuniorDeveloper()) {
            throw IllegalArgumentException()
        }

        return next(request)
    }

val finalResponse = fun() =
    fun(request: Request): Response {
        return Response("최종 결과!")
    }