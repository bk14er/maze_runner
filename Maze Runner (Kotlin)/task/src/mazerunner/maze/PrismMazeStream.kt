package mazerunner.maze

const val VERTEX_SEPARATOR = "-"
const val LINE_SEPARATOR = "\n"
const val CELL_SEPARATOR = "#"


object PrismMazeStream {

    fun serialize(maze: PrimsMaze): String {
        val mazeInformation = maze.mazeInformation
        val (rows, cols, currentLocation, entranceLocation, exitLocation) = mazeInformation
        val line1 =
            "$rows$VERTEX_SEPARATOR$cols ${currentLocation.serialize()} ${entranceLocation.serialize()} ${exitLocation.serialize()}"

        //val deserializePrismInfo = deserialize(line1)
        //check(deserializePrismInfo == mazeInformation) { "Serialization failed. Current: $mazeInformation \n but after deserialize: $deserializePrismInfo" }

        val line2 =
            maze.cells.joinToString(LINE_SEPARATOR) { row -> row.joinToString(CELL_SEPARATOR) { it.serialize() } }

        return "$line1\n$line2"
    }

    fun deserialize(lines: List<String>): Maze {
        val mazeInformationSerialized = lines[0]

        val (rowsCols, currentLocation, entranceLocation, exitLocation) = mazeInformationSerialized.split(" ")
        val (rows, cols) = rowsCols.split(VERTEX_SEPARATOR).map { it.toInt() }
        val mazeInformation = PrismMazeInformation(
            rows, cols,
            deserializeVertex(currentLocation),
            deserializeVertex(entranceLocation),
            deserializeVertex(exitLocation)
        )

        val cells = lines.drop(1).map { row ->
            row.split(CELL_SEPARATOR).map { vertexSerialized -> deserializeVertex(vertexSerialized) }
                .toMutableList()
        }


        return PrimsMaze(mazeInformation, cells)
            .apply { loadMaze() }
    }

    private fun deserializeVertex(vertexSerialized: String): PrimsVertex {
        val (rows, cols, symbol) = vertexSerialized.split(VERTEX_SEPARATOR)
        return PrimsVertex(rows.toInt(), cols.toInt(), Symbol.valueOf(symbol))
    }

}