package presentation.CommandProcessor;

import java.sql.SQLException;

public class CommandProcessor {
    private static CommandProcessor commandProcessor = null;

    public static CommandProcessor makeCommandProcessor() {
        if (commandProcessor == null) {
            commandProcessor = new CommandProcessor();
        }
        
        return commandProcessor;
    }

    private CommandProcessor() {

    }

    public void execute(Command cmd)  throws SQLException, ClassNotFoundException{
        cmd.execute();
    }

}
