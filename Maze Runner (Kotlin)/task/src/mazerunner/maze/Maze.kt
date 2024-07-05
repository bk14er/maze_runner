package mazerunner.maze

interface Maze {

    fun printMaze()

    fun serialize(): String

    fun findTheEscape()

    fun updateVertexAt(updated: PrimsVertex)

}


