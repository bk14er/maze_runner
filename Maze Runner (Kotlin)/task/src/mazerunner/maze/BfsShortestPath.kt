package mazerunner.maze

import mazerunner.maze.VertexFactory.toEscapeVertex
import java.util.LinkedList

/**
 * This class is responsible for finding the shortest path from the start vertex to the end vertex
 *
 * https://pencilprogrammer.com/algorithms/shortest-path-in-unweighted-graph-using-bfs/
 */
class BfsShortestPath(
    private val maze: PrimsMaze
) {

    fun findTheEscape() {
        val startVertex = maze.mazeInformation.entranceLocation
        val endVertex = maze.mazeInformation.exitLocation

        val queue = LinkedList<BfsNode>()
        val alreadyVisited = ArrayList<BfsNode>()

        val startNode = BfsNode(startVertex)
        val endNode = BfsNode(endVertex)

        startNode.visited = true
        alreadyVisited.add(startNode)
        queue.add(startNode)

        while (queue.isNotEmpty()) {
            val currentNode = queue.poll()
            currentNode.calculateNeighbors(maze, alreadyVisited)

            for (neighbor in currentNode.neighbors!!) {
                if (!neighbor.visited) {
                    neighbor.visited = true
                    neighbor.prev = currentNode
                    alreadyVisited.add(neighbor)

                    if (neighbor.vertex.row == endNode.vertex.row && neighbor.vertex.col == endNode.vertex.col) {
                        queue.clear()

                        // Update end vertex
                        maze.updateVertexAt(neighbor.vertex.toEscapeVertex())

                        var prev = neighbor.prev
                        while (prev != null) {
                            maze.updateVertexAt(prev.vertex.toEscapeVertex())
                            prev = prev.prev
                        }
                        return
                    }

                    queue.add(neighbor)
                }
            }

        }
        error("Path not found")
    }

}