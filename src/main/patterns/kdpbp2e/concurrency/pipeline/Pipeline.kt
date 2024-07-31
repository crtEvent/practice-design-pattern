package kdpbp2e.concurrency.pipeline

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit

/* [Pipeline]
 * 다양한 종류의 작업을 큰 규모로 처리할 때 도움이 된다
 * 긴 처리 과정을 작은 세부 단계로 쪼개는 기법
 */

fun main() {
    runBlocking {
        val pagesProducer = producePages()
        val domProducer = produceDom(pagesProducer)
        val titleProducer = produceTitles(domProducer)
        titleProducer.consumeEach {
            println(it)
        }
    }
}

/**
 * HTML 페이지를 가져온다
 */
fun CoroutineScope.producePages() = produce {
    fun getPages(): List<String> {
        return listOf(
            "<html><body><h1>제목 1</h1></body></html>",
            "<html><body><h1>제목 2</h1></body></html>",
        )
    }

    val pages = getPages()

    while (this.isActive) { // isActive: 코루틴이 실행되는 동안은 true
        for (p in pages) {
            send(p)
        }
        delay(TimeUnit.SECONDS.toMillis(5))
    }
}

/**
 * HTML 문자열로 DOM 생성
 */
fun CoroutineScope.produceDom(pages: ReceiveChannel<String>) = produce {
    fun parseDom(page: String) : Document {
        return Document(page)
    }

    for (p in pages) {
        send(parseDom(p))
    }
}

/**
 * HTML DOM 에서 제목 추출
 */
fun CoroutineScope.produceTitles(parsedPages: ReceiveChannel<Document>) = produce {
    fun getTitles(dom: Document): List<String> {
        return dom.getElementsByTagName("h1").map {
            it.toString()
        }
    }

    for (page in parsedPages) {
        for (t in getTitles(page)) {
            send(t)
        }
    }
}

data class Document(val html: String) {
    fun getElementsByTagName(tag: String): List<Document> {
        return listOf(Document("Some title"))
    }
}
