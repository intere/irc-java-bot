package com.intere.bot.irc;

import net.sf.jerklib.ConnectionManager;
import net.sf.jerklib.Profile;
import net.sf.jerklib.Session;
import net.sf.jerklib.events.*;
import net.sf.jerklib.events.IRCEvent.Type;
import net.sf.jerklib.listeners.IRCEventListener;

/**
 * Hello world!
 *
 */
public class App implements IRCEventListener
{
	private ConnectionManager manager;

	public App() {

		//public Profile(String name, String realName, String nick, String secondNick, String thirdNick)
		Profile profile = new Profile("Robotnik", "Dr. Robotnik", "robotnik", "robot_nik", "dr_robot_nik");

		/*
		 * ConnectionManager takes a Profile to use for new connections.
		 */
		manager = new ConnectionManager(profile);


		/*
		 * One instance of ConnectionManager can connect to many IRC networks.
		 * ConnectionManager#requestConnection(String) will return a Session object.
		 * The Session is the main way users will interact with this library and IRC
		 * networks
		 */
		String channel = "irc.freenode.net";
		// String channel = "irc.eversible.com";

		Session session = manager.requestConnection(channel);
 
		/*
		 * JerkLib fires IRCEvents to notify users of the lib of incoming events
		 * from a connected IRC server.
		 */
		session.addIRCEventListener(this);
	}

	/*
	 * This method is for implementing an IRCEventListener. This method will be
	 * called anytime Jerklib parses an event from the Session its attached to.
	 * All events are sent as IRCEvents. You can check its actual type and cast it
	 * to a more specific type.
	 */
	public void receiveEvent(IRCEvent e)
	{
		if (e.getType() == Type.CONNECT_COMPLETE)
		{
			e.getSession().join("#orbittheworld");
 
		}
		else if (e.getType() == Type.CHANNEL_MESSAGE)
		{
			MessageEvent me = (MessageEvent) e;
			System.out.println("<" + me.getNick() + ">"+ ":" + me.getMessage());
		}
		else if (e.getType() == Type.JOIN_COMPLETE)
		{
			JoinCompleteEvent jce = (JoinCompleteEvent) e;
 
			/* say hello and version number */
			jce.getChannel().say("Hello from Jerklib " + ConnectionManager.getVersion());
		}
		else
		{
			System.out.println(e.getType() + " " + e.getRawEventData());
		}
	}

    public static void main( String[] args )
    {
    	new App();
    }
}
