package org.kaminodroid.web;

import org.apache.wicket.markup.html.WebPage;
import org.kaminodroid.client.KaminoDroidClient;

public abstract class BaseWebPage extends WebPage {

	public KaminoDroidClient getRestClient() {
		return ((KaminoDroidWebApp) getApplication()).getRestClient();
	}

}
