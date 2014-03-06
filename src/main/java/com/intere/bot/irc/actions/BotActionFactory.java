

package com.intere.bot.irc.actions;

import com.intere.bot.irc.ChannelHandler;
import net.sf.jerklib.events.MessageEvent;
import net.sf.jerklib.Channel;

/** Handles creation of a Bot Action for you. */
public class BotActionFactory {
	
	private ChannelHandler handler = new ChannelHandler();

	/** Creates and hands back a Bot Action (when appropriate).  */
	public BotAction createBotAction(MessageEvent event) {

		String command = handler.getCommand(event.getMessage());

		if(null!=command) {
			String params[] = handler.getParameters(event.getMessage());

			return getBot(command, params, event);
		}


		return null;
	}


	private BotAction getBot(String command, String[] params, MessageEvent event) {

		if("count".equals(command)) {
			return new CountBotAction(command, params, event);
		}

		return null;
	}


}
