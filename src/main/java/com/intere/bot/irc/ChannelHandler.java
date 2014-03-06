
package com.intere.bot.irc;


/**
 * Responsible for determining whether or not an action should be taken
 * on a command.
 */
public class ChannelHandler {
	
	/** Is the provided message string a bot command?  */
	public boolean isCommand(String message) {
		return null != message &&
			message.trim().startsWith("!");
	}

	/** Get me the actual command (strips out the ! character). */
	public String getCommand(String message) {
		
		if(isCommand(message)) {
			message = message.trim();
			if(message.indexOf(' ') != -1) {
				return message.substring(1, message.indexOf(' '));
			}

			return message.substring(1);			
		}

		return null;
	}

	/** Get me the parameters for the provided message.  */
	public String[] getParameters(String message) {

		if(isCommand(message)) {
			String parts[] = message.trim().split(" ");
			if(parts.length>1) {
				String params[] = new String[parts.length-1];
				for(int i=1;i<parts.length;i++) {
					params[i-1] = parts[i];
				}
				return params;
			}

		}

		return null;
	}
}