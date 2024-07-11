package kdpbp2e.creational.singleton

fun main() {
    val myFavoriteMovies = NoMoviesList
    val yourFavoriteMovies = NoMoviesList

    println(myFavoriteMovies === yourFavoriteMovies) // true

    printMovies(myFavoriteMovies)
}

fun printMovies(movies: List<String>) {
    for (m in movies) {
        println(m)
    }
}

object NoMoviesList: List<String> {
    override val size: Int = 0

    override fun get(index: Int): String {
        TODO("Not yet implemented")
    }

    override fun isEmpty(): Boolean = true

    override fun iterator(): Iterator<String> = emptyList<String>().iterator()

    override fun listIterator(): ListIterator<String> = emptyList<String>().listIterator()

    override fun listIterator(index: Int): ListIterator<String> {
        if (index != 0) throw IndexOutOfBoundsException("This list is always empty.")
        return emptyList<String>().listIterator()
    }

    override fun subList(fromIndex: Int, toIndex: Int): List<String> {
        if (fromIndex != 0 || toIndex != 0) throw IndexOutOfBoundsException("This list is always empty.")
        return emptyList()
    }

    override fun toArray(): Array<Any> {
        TODO("Not yet implemented")
    }

    override fun <T : Any?> toArray(a: Array<out T>): Array<T> {
        TODO("Not yet implemented")
    }

    override fun lastIndexOf(element: String): Int = -1

    override fun indexOf(element: String): Int = -1

    override fun containsAll(elements: Collection<String>): Boolean = false

    override fun contains(element: String): Boolean = false

}