
package com.intere.bot.irc.actions;

import net.sf.jerklib.events.MessageEvent;

public class CountBotAction extends BotAction {

	private int from = 0;
	private int to = 10;
	
	public CountBotAction(String command, String[] params, MessageEvent event) {
		super(command, params, event);
		parseParams();
	}

	private void parseParams() {

		if(null != getParams()) {
			boolean nextIsTo = false;
			boolean nextIsFrom = false;

			for(String param : getParams()) {
				if(nextIsFrom) {
					from = Integer.parseInt(param)-1;
					nextIsFrom = false;
				} else if(nextIsTo) {
					to = Integer.parseInt(param);
					nextIsTo = false;
				} else if("from".equalsIgnoreCase(param)) {
					nextIsFrom = true;
				} else if("to".equalsIgnoreCase(param)) {
					nextIsTo = true;
				}
			}
		}

	}

	@Override
	public void execute() {

		getEvent().getChannel().say("I will count for you...");

		for(int i=from; i<to; i++) {
			getEvent().getChannel().say(String.valueOf(i+1));
		}

	}
}
