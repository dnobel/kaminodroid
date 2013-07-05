package org.kaminodroid.standalone;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class KaminoDroidServer {
	public static void main(String[] args) {
		Server server = new Server(8080);
		WebAppContext webapp = new WebAppContext();
		webapp.setContextPath("/");
		webapp.setWar("kaminodroid-backend.war");
		server.setHandler(webapp);

		try {
			server.start();
			System.in.read();
			server.stop();
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(100);
		}
	}
}
