package org.kaminodroid.standalone;

import java.net.URL;
import java.security.ProtectionDomain;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class KaminoDroidServer {
	public static void main(String[] args) {

		Server server = new Server(8080);
		org.eclipse.jetty.webapp.Configuration.ClassList classlist = org.eclipse.jetty.webapp.Configuration.ClassList
				.setServerDefault(server);
		classlist.addAfter("org.eclipse.jetty.webapp.FragmentConfiguration",
				"org.eclipse.jetty.plus.webapp.EnvConfiguration", "org.eclipse.jetty.plus.webapp.PlusConfiguration");
		classlist.addBefore("org.eclipse.jetty.webapp.JettyWebXmlConfiguration",
				"org.eclipse.jetty.annotations.AnnotationConfiguration");

		WebAppContext webapp = new WebAppContext();
		webapp.setContextPath("/");
		webapp.setServer(server);
		ProtectionDomain protectionDomain = KaminoDroidServer.class.getProtectionDomain();
		URL location = protectionDomain.getCodeSource().getLocation();
		webapp.setWar(location.toExternalForm());

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
