package mazerunner.maze

import mazerunner.maze.VertexFactory.toEndVertex
import mazerunner.maze.VertexFactory.toPassageVertex
import mazerunner.maze.VertexFactory.toStartVertex
import java.util.*

data class PrismMazeInformation(
    val rows: Int, val cols: Int,
    var currentLocation: PrimsVertex,
    val entranceLocation: PrimsVertex,
    val exitLocation: PrimsVertex
) {

    companion object {
        private val randomizer = Random()

        fun build(rows: Int, cols: Int): PrismMazeInformation {
            return PrismMazeInformation(
                rows, cols,
                selectStartVertex(randomizer.nextInt(1, rows - 1), randomizer.nextInt(1, cols - 1)),
                selectMazeEntrance(randomizer.nextInt(1, rows - 1)),
                selectMazeExit(rows, cols)
            )
        }

        private fun selectStartVertex(startRowInput: Int, startColInput: Int): PrimsVertex {
            // Select a random starting location within the maze, not on the boundary
            var startRow = startRowInput
            var startCol = startColInput

            // Adjust if the starting point falls on a cell that would naturally be a wall
            if (startRow % 2 == 0) startRow++
            if (startCol % 2 == 0) startCol++

            return PrimsVertex(startRow, startCol).toPassageVertex()
        }

        private fun selectMazeEntrance(startEntranceRow: Int): PrimsVertex {
            // Select the entrance on the top row
            val entranceCol = 0
            var entranceRow = startEntranceRow

            if (entranceRow % 2 == 0) {
                entranceRow--  // Adjust for even columns
            }

            return PrimsVertex(entranceRow, entranceCol).toStartVertex()
        }

        private fun selectMazeExit(rows: Int, cols: Int): PrimsVertex {
            var exitRow = randomizer.nextInt(1, rows - 1)
            val exitCol = cols - 1

            if (exitRow % 2 == 0) {
                exitRow--  // Adjust for even columns
            }
            return PrimsVertex(exitRow, exitCol).toEndVertex()
        }

    }
}