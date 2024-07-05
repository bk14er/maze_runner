package mazerunner

import java.util.*

const val PASS = "  "
const val WALL = "██"

private val randomizer: Random = Random()

fun main() {
    println("Please, enter the size of a maze")
    val line = readLine()!!
    var rows = line.split(" ")[0].toInt()
    var cols = line.split(" ")[1].toInt()
    //    var rows = 13
    //    var cols = 9

    val maze = generateMaze(rows, cols)
    printMaze(maze)
}

enum class Symbol(val symbol: Char) {
    PASS(' '),
    WALL('█'),
    START(' '),
    END(' '),
}

class Vertex(val row: Int, val col: Int, val maze: Maze) {

    var parent: Vertex? = null

    private var symbol: Symbol = Symbol.WALL

    fun isPassage(): Boolean {
        if (symbol == Symbol.START || symbol == Symbol.END) return true

        return symbol != Symbol.WALL
        //&& !maze.isWall(row, col)
    }

    override fun toString(): String {
        return "($row, $col)"
    }

    fun isA(vararg expected: Symbol): Boolean {
        return symbol in expected
    }

    fun markAsPassage() {
        symbol = Symbol.PASS
    }

    fun markAsStart() {
        //println("Start $row, $col")
        symbol = Symbol.START
    }

    fun markAsEnd() {
        //println("End $row, $col")
        symbol = Symbol.END
    }

}

interface Maze {
    fun rows(): Int
    fun cols(): Int
    fun isWall(row: Int, col: Int): Boolean
}

class PrimsMaze(val rows: Int, val cols: Int) : Maze {

    private val cells: List<List<Vertex>> = buildEmptyMaze()

    var currentLocation: Vertex = cells[0][0]
    var frontier: ArrayList<Vertex> = ArrayList()

    fun generate() {
        randomlySelectMazeEntranceAndStartingLocation()
        randomlySelectMazeExit()

        searchAllDirections(currentLocation)

        while (!frontier.isEmpty()) {
            currentLocation = moveToRandomFrontier()
            searchAllDirections(currentLocation)
        }

    }

    fun getVertex(row: Int, col: Int): Vertex {
        return cells[row][col]
    }

    fun all(): List<List<Vertex>> {
        return cells.toList()
    }

    private fun randomlySelectMazeEntranceAndStartingLocation() {
        // Select the entrance on the top row
        var entranceRow = randomizer.nextInt(1, rows - 1)
        val entranceCol = 0

        if (entranceRow % 2 == 0) {
            entranceRow--  // Adjust for even columns
        }

        getVertex(entranceRow, entranceCol).markAsStart()  // Mark entrance as passage

        // Select a random starting location within the maze, not on the boundary
        var startRow = randomizer.nextInt(1, rows - 1)
        var startCol = randomizer.nextInt(1, cols - 1)

        // Adjust if the starting point falls on a cell that would naturally be a wall
        if (startRow % 2 == 0) startRow++
        if (startCol % 2 == 0) startCol++

        //println("Starting point $startRow, $startCol")

        currentLocation = getVertex(startRow, startCol)
        currentLocation.markAsPassage()  // Mark the starting location as a passage
    }


    private fun randomlySelectMazeExit() {
        var exitRow = randomizer.nextInt(1, rows - 1)
        val exitCol = cols - 1

        if (exitRow % 2 == 0) {
            exitRow--  // Adjust for even columns
        }

        getVertex(exitRow, exitCol).markAsEnd()
    }

    private fun buildEmptyMaze(): List<List<Vertex>> {
        val result = List(rows) { row ->
            List(cols) { col ->
                Vertex(row, col, this)
            }
        }

        return result
    }

    private fun frontierAlreadyExists(vertex: Vertex): Boolean {
        return frontier.contains(vertex)
    }

    private fun isInBounds(row: Int, col: Int): Boolean {
        return row in 1 until rows - 1 && col in 1 until cols - 1
    }

    private fun searchAllDirections(current: Vertex) {
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

    private fun moveToRandomFrontier(): Vertex {
        val nextVertex = frontier.random()

        nextVertex.markAsPassage()

        // exclude vertex around the rectangles
        removeFromFrontierList(nextVertex)
        removeWallBetweenVertices(nextVertex)

        return nextVertex
    }

    private fun removeWallBetweenVertices(newVertex: Vertex) {
        val parent: Vertex = newVertex.parent!!

        //remove wall to the SOUTH of current location
        if (newVertex.row > parent.row) {
            getVertex(parent.row + 1, parent.col).markAsPassage()
            //remove wall to the NORTH of current location
        } else if (newVertex.row < parent.row) {
            getVertex(parent.row - 1, parent.col).markAsPassage()
            //remove wall to the RIGHT of current location
        } else if (newVertex.col > parent.col) {
            getVertex(parent.row, parent.col + 1).markAsPassage()
            //remove wall to the LEFT of currentLocation
        } else if (newVertex.col < parent.col) {
            getVertex(parent.row, parent.col - 1).markAsPassage()
        }
    }

    private fun removeFromFrontierList(vertex: Vertex) {
        val remove = frontier.remove(vertex)

        if (!remove) {
            throw IllegalStateException("Failed to remove $vertex")
        }
    }

    override fun rows(): Int = rows

    override fun cols(): Int = cols

    override fun isWall(row: Int, col: Int): Boolean =
        row == 0 || col == 0 || row == rows - 1 || col == cols - 1


}

private fun generateMaze(rows: Int, cols: Int): PrimsMaze {
    val maze = PrimsMaze(rows, cols)

    maze.generate()

    return maze
}

private fun printMaze(maze: PrimsMaze) {
    for (row in maze.all()) {
        for (cell in row) {
            print(if (cell.isPassage()) PASS else WALL)
        }
        println()
    }
}

