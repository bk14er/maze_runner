package mazerunner

import mazerunner.maze.PrimsMaze
import mazerunner.maze.PrismMazeInformation
import mazerunner.maze.PrismMazeStream
import java.io.File


data class MenuItem(val command: Int, val description: String)

sealed class ListMenu {

    abstract fun displayCommands(): List<MenuItem>

    abstract fun handleCommand(command: Int, context: CommandContext): CommandContext

    fun handleGenerateNewMaze(): CommandContext {
        println("Please, enter the size of a maze")
        val size = readLine()!!.toInt()


        return FullCommandContext(PrimsMaze(PrismMazeInformation.build(size, size))
            .apply { generateNewMaze() }
            .apply { printMaze() }
        )
    }

    fun handleLoadMazeFromFile(currentContext: CommandContext): CommandContext {
        println("Please, enter the file name")
        val fileName = readLine()!!
        val file = File(fileName)

        if (!file.exists()) {
            return when (currentContext) {
                is StartingCommandContext -> {
                    StartingCommandContext(CommandResult.INVALID_FILE)
                }

                is FullCommandContext -> {
                    FullCommandContext(currentContext.currentMaze, CommandResult.INVALID_FILE)
                }
            }
        }

        val fileLines = file.readLines()

        if (fileLines.isEmpty()) {
            return when (currentContext) {
                is StartingCommandContext -> {
                    StartingCommandContext(CommandResult.INVALID_FILE_CONTENT)
                }

                is FullCommandContext -> {
                    FullCommandContext(currentContext.currentMaze, CommandResult.INVALID_FILE_CONTENT)
                }
            }
        }

        return FullCommandContext(PrismMazeStream.deserialize(fileLines))
    }

    fun handleDisplayMaze(context: CommandContext): CommandContext = when (context) {
        is FullCommandContext -> {
            context.currentMaze.printMaze()
            context
        }

        else -> {
            throw IllegalStateException("Invalid context")
        }
    }

    fun handleSaveMazeToFile(context: CommandContext): CommandContext {
        val fileName = readLine()!!

        val file = File(fileName)

        when (context) {
            is FullCommandContext -> {
                file.writeText(context.currentMaze.serialize())
            }

            else -> {
                throw IllegalStateException("Invalid context")
            }
        }

        return context
    }

    fun handleEscape(context: CommandContext): CommandContext = when (context) {
        is FullCommandContext -> {
            context.currentMaze.findTheEscape()
            context
        }

        else -> {
            throw IllegalStateException("Invalid context")
        }
    }

}

object StartingListMenu : ListMenu() {

    override fun displayCommands(): List<MenuItem> =
        listOf(
            MenuItem(1, "Generate a new maze"),
            MenuItem(2, "Load a maze"),
            MenuItem(0, "Exit")
        )

    override fun handleCommand(command: Int, currentContext: CommandContext): CommandContext =
        when (command) {
            0 -> StartingCommandContext(CommandResult.EXIT)
            1 -> handleGenerateNewMaze()
            2 -> handleLoadMazeFromFile(currentContext)
            else -> StartingCommandContext(CommandResult.INVALID_COMMAND)
        }

}

object FullListMenu : ListMenu() {

    override fun displayCommands(): List<MenuItem> =
        listOf(
            MenuItem(1, "Generate a new maze."),
            MenuItem(2, "Load a maze."),
            MenuItem(3, "Save the maze."),
            MenuItem(4, "Display the maze."),
            MenuItem(5, "Find the escape."),
            MenuItem(0, "Exit")
        )

    override fun handleCommand(command: Int, context: CommandContext): CommandContext {
        when (context) {
            is FullCommandContext -> context
            else -> throw IllegalStateException("Invalid context")
        }

        return when (command) {
            0 -> FullCommandContext(context.currentMaze, CommandResult.EXIT)
            1 -> handleGenerateNewMaze()
            2 -> handleLoadMazeFromFile(context)
            3 -> handleSaveMazeToFile(context)
            4 -> handleDisplayMaze(context)
            5 -> handleEscape(context)
            else -> FullCommandContext(context.currentMaze, CommandResult.INVALID_COMMAND)
        }
    }

}
