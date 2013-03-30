package org.kaminodroid.web;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.kaminodroid.client.KaminoDroidClient;

public class KaminoDroidWebApp extends WebApplication {

	private final KaminoDroidClient restClient;

	public KaminoDroidWebApp() {
		restClient = new KaminoDroidClient("http://localhost:8080/rest/");
	}

	@Override
	public Class<? extends Page> getHomePage() {
		return HomePage.class;
	}

	public KaminoDroidClient getRestClient() {
		return restClient;
	}

}
