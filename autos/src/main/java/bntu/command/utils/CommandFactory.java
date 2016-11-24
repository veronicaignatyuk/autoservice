package main.java.bntu.command.utils;

import main.java.bntu.command.*;

/**
 * Command Factory pattern
 * 
 * @author Veronika
 *
 */
public class CommandFactory {

	/**
	 * Defines every command
	 * 
	 * @param paramCommand
	 * @return
	 */
	public static Command getCommand(String paramCommand) {
		CommandType commandType = CommandType.valueOf(paramCommand
				.toUpperCase());
		return commandType.getCommand();
	}
}
