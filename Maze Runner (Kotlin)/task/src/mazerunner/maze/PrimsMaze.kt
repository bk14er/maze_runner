package mazerunner.maze

import mazerunner.maze.VertexFactory.toPassageVertex
import kotlin.collections.ArrayList

class PrimsMaze(
    val mazeInformation: PrismMazeInformation,
    val cells: List<MutableList<PrimsVertex>> = buildEmptyMaze(mazeInformation)
) : Maze {

    private lateinit var frontier: ArrayList<PrimsVertex>

    fun generateNewMaze() {
        loadMaze()

        frontier = ArrayList()
        searchAllDirections(mazeInformation.currentLocation)
        while (frontier.isNotEmpty()) {
            mazeInformation.currentLocation = moveToRandomFrontier()
            searchAllDirections(mazeInformation.currentLocation)
        }
    }

    fun loadMaze() {
        updateVertexAt(mazeInformation.entranceLocation)
        updateVertexAt(mazeInformation.exitLocation)
    }

    override fun printMaze() {
        for (row in cells.toList()) {
            for (cell in row) {
                print(cell.toString())
            }
            println()
        }
    }

    override fun serialize(): String {
        return PrismMazeStream.serialize(this)
    }

    override fun findTheEscape() {
        val copyCells = cells.map { it.toMutableList() }
        val holder = PrimsMaze(mazeInformation, copyCells)
        val path = BfsShortestPath(holder)
        path.findTheEscape()
        holder.printMaze()
    }

    override fun updateVertexAt(updated: PrimsVertex) {
        cells[updated.row][updated.col] = updated
    }

    private fun getVertex(row: Int, col: Int): PrimsVertex = cells[row][col]

    private fun frontierAlreadyExists(vertex: PrimsVertex) = frontier.contains(vertex)

    private fun isInBounds(row: Int, col: Int) =
        row in 1 until mazeInformation.rows - 1 && col in 1 until mazeInformation.cols - 1

    private fun searchAllDirections(current: PrimsVertex) {
        val currentRow: Int = current.row
        val currentCol: Int = current.col

        //look left
        if (isInBounds(currentRow, currentCol - 2) &&
            getVertex(currentRow, currentCol - 2).isA(Symbol.WALL) &&
            getVertex(currentRow, currentCol - 1).isA(Symbol.WALL)
        ) {
            getVertex(currentRow, currentCol - 2).parent = current
            if (!frontierAlreadyExists(getVertex(currentRow, currentCol - 2))) {
                frontier.add(getVertex(currentRow, currentCol - 2))
            }
        }

        //look right
        if (isInBounds(currentRow, currentCol + 2) &&
            getVertex(currentRow, currentCol + 2).isA(Symbol.WALL) &&
            getVertex(currentRow, currentCol + 1).isA(Symbol.WALL)
        ) {
            getVertex(currentRow, currentCol + 2).parent = current
            if (!frontierAlreadyExists(getVertex(currentRow, currentCol + 2))) {
                frontier.add(getVertex(currentRow, currentCol + 2))
            }
        }

        //look down
        if (isInBounds(currentRow - 2, currentCol) &&
            getVertex(currentRow - 2, currentCol).isA(Symbol.WALL) &&
            getVertex(currentRow - 1, currentCol).isA(Symbol.WALL)
        ) {
            getVertex(currentRow - 2, currentCol).parent = current
            if (!frontierAlreadyExists(getVertex(currentRow - 2, currentCol))) {
                frontier.add(getVertex(currentRow - 2, currentCol))
            }
        }

        //look up
        if (isInBounds(currentRow + 2, currentCol) &&
            getVertex(currentRow + 2, currentCol).isA(Symbol.WALL) &&
            getVertex(currentRow + 1, currentCol).isA(Symbol.WALL)
        ) {

            getVertex(currentRow + 2, currentCol).parent = current
            if (!frontierAlreadyExists(getVertex(currentRow + 2, currentCol))) {
                frontier.add(getVertex(currentRow + 2, currentCol))
            }
        }


    }

    private fun moveToRandomFrontier(): PrimsVertex {
        val nextVertex = frontier.random()
        updateVertexAt(nextVertex.toPassageVertex())

        // exclude vertex around the rectangles
        removeFromFrontierList(nextVertex)
        removeWallBetweenVertices(nextVertex)

        return nextVertex
    }

    private fun removeWallBetweenVertices(newVertex: PrimsVertex) {
        val parent: PrimsVertex = newVertex.parent!!

        //remove wall to the SOUTH of current location
        if (newVertex.row > parent.row) {
            updateVertexAt(getVertex(parent.row + 1, parent.col).toPassageVertex())
            //remove wall to the NORTH of current location
        } else if (newVertex.row < parent.row) {
            updateVertexAt(getVertex(parent.row - 1, parent.col).toPassageVertex())
            //remove wall to the RIGHT of current location
        } else if (newVertex.col > parent.col) {
            updateVertexAt(getVertex(parent.row, parent.col + 1).toPassageVertex())
            //remove wall to the LEFT of currentLocation
        } else if (newVertex.col < parent.col) {
            updateVertexAt(getVertex(parent.row, parent.col - 1).toPassageVertex())
        }
    }

    private fun removeFromFrontierList(vertex: PrimsVertex) {
        val remove = frontier.remove(vertex)

        if (!remove) {
            throw IllegalStateException("Failed to remove $vertex")
        }
    }

    companion object {
        fun buildEmptyMaze(mazeInformation: PrismMazeInformation): List<MutableList<PrimsVertex>> =
            List(mazeInformation.rows) { row ->
                MutableList(mazeInformation.cols) { col ->
                    PrimsVertex(row, col, Symbol.WALL)
                }
            }
    }

}