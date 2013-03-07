package edu.gatech.cs2340.whereismystuff.commands;

import java.util.Stack;

/**
 * This is a singleton that handles all the actual doing and undoing of commands
 * 
 * @author Robert
 *
 */
public class CommandProcessor {
	private static CommandProcessor instance = new CommandProcessor();
	
	private Stack<ICommand> undoStack;
	private Stack<ICommand> redoStack;
	
	public static CommandProcessor getInstance() { return instance; }
	
	private CommandProcessor() { 
		undoStack = new Stack<ICommand>();
		redoStack = new Stack<ICommand>();
	}
	
	public void execute(ICommand cmd) {
		if (cmd.execute()) {
		   undoStack.push(cmd);
		} else {
			undoStack.clear();
		}
		
	}
	
	public void undoLast() {
		if (undoStack.isEmpty()) return;
		ICommand cmd = undoStack.pop();
		cmd.undo();
		redoStack.push(cmd);
	}
	
	public void redoLast() {
		if (redoStack.isEmpty()) return;
		ICommand cmd = redoStack.pop();
		execute(cmd);
	}

}
