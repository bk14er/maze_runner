package mazerunner

import mazerunner.maze.Maze


sealed interface CommandContext {
    fun getMenu(): ListMenu

    fun lastExecutionResult(): CommandResult
}


enum class CommandResult {
    CONTINUE,
    EXIT,
    INVALID_COMMAND,
    INVALID_FILE,
    INVALID_FILE_CONTENT,
    UNKNOWN
}


class StartingCommandContext(private val lastExecutionResult: CommandResult = CommandResult.UNKNOWN) : CommandContext {
    override fun getMenu(): ListMenu {
        return StartingListMenu
    }

    override fun lastExecutionResult(): CommandResult = lastExecutionResult

}

class FullCommandContext(val currentMaze: Maze, private val lastExecutionResult: CommandResult = CommandResult.CONTINUE) : CommandContext {

    override fun getMenu(): ListMenu {
        return FullListMenu
    }

    override fun lastExecutionResult(): CommandResult = lastExecutionResult

}
