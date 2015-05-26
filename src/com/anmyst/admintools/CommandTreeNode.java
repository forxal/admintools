package com.anmyst.admintools;

/**
 * Command and Folder information (Tree node)
 */
public class CommandTreeNode {
	private String itemName;  //command name
	private String itemCommand; //command text
	private boolean folder; //true - folder, false - command
	
	public CommandTreeNode(String name, String command, boolean f) {
		itemName = name;
		itemCommand = command;		
		folder = f;
	}
	
	public String getName() {
		return itemName;
	}
	
	public String getCommand() {
		return itemCommand;
	}
	
	public boolean isFolder() {
		return folder;
	}
	
	@Override
	public String toString() {
		return itemName;
	}

}
