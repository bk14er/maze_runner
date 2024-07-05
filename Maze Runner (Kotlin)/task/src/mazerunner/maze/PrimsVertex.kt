package mazerunner.maze

private const val PASS = "  "
private const val WALL = "██"

class PrimsVertex(override val row: Int, override val col: Int, override val symbol: Symbol = Symbol.WALL) : Vertex {
    var parent: PrimsVertex? = null

    fun isPassage(): Boolean {
        if (symbol == Symbol.START || symbol == Symbol.END) return true
        return symbol != Symbol.WALL && symbol != Symbol.ESCAPE
    }

    fun isA(vararg expected: Symbol) = symbol in expected

    override fun toString(): String = when {
        isPassage() -> PASS
        symbol == Symbol.ESCAPE -> "//"
        else -> WALL
    }

    fun toStringDebug(): String = "PrimsVertex(row=$row, col=$col, symbol=$symbol, parent=$parent)"

    fun serialize(): String = "$row$VERTEX_SEPARATOR$col$VERTEX_SEPARATOR$symbol"

    override fun hashCode(): Int {
        var result = row
        result = 31 * result + col
        result = 31 * result + symbol.hashCode()
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PrimsVertex) return false

        if (row != other.row) return false
        if (col != other.col) return false
        if (symbol != other.symbol) return false

        return true
    }

}

