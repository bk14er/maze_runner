package mazerunner.maze

class BfsNode(val vertex: PrimsVertex) {
    var visited: Boolean = false
    var neighbors: MutableList<BfsNode>? = null
    var prev: BfsNode? = null


    override fun toString(): String {
        return "BfsNode(vertex=${vertex.toStringDebug()}, visited=$visited, neighbors=${
            neighbors?.forEach { it.vertex.toStringDebug() }.toString()
        }, prev = ${prev?.vertex?.toStringDebug()})"
    }

    override fun hashCode(): Int {
        return vertex.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is BfsNode) return false

        if (vertex != other.vertex) return false

        return true
    }

    fun calculateNeighbors(maze: PrimsMaze, alreadyVisited: ArrayList<BfsNode>) {
        val neighbors = mutableListOf<BfsNode>()
        val row = vertex.row
        val col = vertex.col

        if (row > 0) {
            val top = maze.cells[row - 1][col]
            if (top.isPassage()) {
                neighbors.add(BfsNode(top))
            }
        }

        if (row < maze.mazeInformation.rows - 1) {
            val bottom = maze.cells[row + 1][col]
            if (bottom.isPassage()) {
                neighbors.add(BfsNode(bottom))
            }
        }

        if (col > 0) {
            val left = maze.cells[row][col - 1]
            if (left.isPassage()) {
                neighbors.add(BfsNode(left))
            }
        }

        if (col < maze.mazeInformation.cols - 1) {
            val right = maze.cells[row][col + 1]
            if (right.isPassage()) {
                neighbors.add(BfsNode(right))
            }
        }

        neighbors.replaceAll{
            if (alreadyVisited.contains(it)) {
                alreadyVisited[alreadyVisited.indexOf(it)]
            } else {
                it
            }
        }

        this.neighbors = neighbors
    }


}