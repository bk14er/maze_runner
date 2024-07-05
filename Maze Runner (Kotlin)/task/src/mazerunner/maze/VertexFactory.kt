package mazerunner.maze

object VertexFactory {
    fun PrimsVertex.toPassageVertex() = PrimsVertex(row, col, Symbol.PASS)
    fun PrimsVertex.toStartVertex() = PrimsVertex(row, col, Symbol.START)
    fun PrimsVertex.toEndVertex() = PrimsVertex(row, col, Symbol.END)
    fun PrimsVertex.toEscapeVertex() = PrimsVertex(row, col, Symbol.ESCAPE)
}