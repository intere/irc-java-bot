
package com.intere.bot.irc;


import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test for the ChannelHandler class.
 */
public class ChannelHandlerTest {

	public static final String COMMANDS[] = new String[] { "!run something", "!exec", "!fly to the moon", " !whoah tricky!", "	 !roads" };
	public static final String NON_COMMANDS[] = new String[] { null, "", "run away", "execute him", "take them to the iron maiden", "     ", "   something ! fish" };
	private ChannelHandler handler;

	@Before
	public void setUp() {
		handler = new ChannelHandler();
	}


	@Test
	public void testIsCommand() {
		for(String cmd : COMMANDS) {
			assertTrue(handler.isCommand(cmd));
		}

		for(String nonCmd : NON_COMMANDS) {
			assertFalse(handler.isCommand(nonCmd));
		}
	}

	@Test
	public void testGetCommand() {

		for(String cmd : COMMANDS) {

			cmd = cmd.trim();

			String expected = cmd.indexOf(' ')!=-1 ? cmd.substring(1,cmd.indexOf(' ')) : cmd.substring(1);
			
			assertEquals("The command didn't line up", expected, handler.getCommand(cmd));
		}

		for(String nonCmd : NON_COMMANDS) {
			assertNull("The command handler should have given a null back", handler.getCommand(nonCmd));
		}
	}

	@Test
	public void testGetParams() {

		for(String cmd : COMMANDS) {
			String parts[] = cmd.trim().split(" ");
			String actual[] = handler.getParameters(cmd);

			if(parts.length>1) {
				assertEquals("There should be exactly " + (parts.length-1) + " parts", (parts.length-1), actual.length);
				for(int i=0; i<actual.length; i++) {
					assertEquals("Parameters are out of alignment", parts[i+1], actual[i]);
				}
			} else {
				assertNull("There should be no parameters", actual);
			}
		}


		for(String nonCmd : NON_COMMANDS) {
			assertNull("The command handler should have given a null back", handler.getParameters(nonCmd));
		}
	}
}

