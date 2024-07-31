package kdpbp2e.creational.factorymethod

fun main() {
    val notations: List<String> = listOf("pa8", "qc3")
    val pieces: MutableList<ChessPiece> = mutableListOf()
    for (n in notations) {
        pieces.add(createPiece(n))
    }
    println(pieces)
}

interface ChessPiece {
    val file: Char
    val rank: Char
}

data class Pawn(
    override val file: Char,
    override val rank: Char
) : ChessPiece


data class Queen(
    override val file: Char,
    override val rank: Char
) : ChessPiece

fun createPiece(notation: String): ChessPiece {
    val (type, file, rank) = notation.toCharArray()
    return when (type) {
        'q' -> Queen(file, rank)
        'p' -> Pawn(file, rank)
        else -> throw RuntimeException("Unknown piece: $type")
    }
}