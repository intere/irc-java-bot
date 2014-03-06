package com.intere.bot.irc.actions;

import net.sf.jerklib.events.MessageEvent;

/** Actions that the bot executes.  */
public abstract class BotAction {

	private String command;
	private String[] params;
	private MessageEvent event;

	/** Constructor - sets the basic BotAction data.  */
	public BotAction(String command, String[] params, MessageEvent event) {
		this.command = command;
		this.params = params;
		this.event = event;
	}

	/** Executes this action.  */
	public abstract void execute();

	/** Getter for command.  */
	public String getCommand() {
		return command;
	}

	/** Getter for parameters.  */
	public String[] getParams() {
		return params;
	}

	/** Getter for the event object.  */
	public MessageEvent getEvent() {
		return event;
	}

}
