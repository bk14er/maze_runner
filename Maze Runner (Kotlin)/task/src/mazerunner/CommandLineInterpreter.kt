package mazerunner

object CommandLineInterpreter {

    fun execute() {
        var currentContext: CommandContext = StartingCommandContext()

        while (true) {
            val menu = currentContext.getMenu()

            println("=== Menu ===")
            menu.displayCommands().forEach { menuItem ->
                println("${menuItem.command}. ${menuItem.description}")
            }

            currentContext = menu.handleCommand(readln().toInt(), currentContext)
            val result = currentContext.lastExecutionResult()

            when (result) {
                CommandResult.EXIT -> {
                    println("Bye!")
                    break
                }
                CommandResult.INVALID_COMMAND -> println("Incorrect option. Please try again")
                CommandResult.UNKNOWN -> println("Unknown command. Please try again")
                CommandResult.INVALID_FILE -> println("File does not exist")
                CommandResult.INVALID_FILE_CONTENT -> println("Cannot load the maze. It has an invalid format.")
                CommandResult.CONTINUE -> {
                    // do nothing - continue processing
                }
            }


        }

    }

}
