package edu.gatech.cs2340.whereismystuff.commands;

public interface ICommand {
	boolean execute();
	void undo();
}
