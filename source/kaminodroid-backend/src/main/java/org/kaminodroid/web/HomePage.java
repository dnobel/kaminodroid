package org.kaminodroid.web;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.kaminodroid.api.Application;
import org.kaminodroid.api.Artifact;

public class HomePage extends BaseWebPage {
	public HomePage() {
		// add(new AjaxLazyLoadPanel("application-list") {
		//
		// private static final long serialVersionUID = -7858071205850065695L;
		//
		// @Override
		// public Component getLazyLoadComponent(String markupId) {
		// return new ApplicationListPanel(markupId);
		// }
		//
		// });
		add(new ApplicationListView("application-list-item", getApplications()));
	}

	private List<Application> getApplications() {
		return HomePage.this.getRestClient().getApplications();
	}

	private class ApplicationListPanel extends Panel {

		public ApplicationListPanel(String id) {
			super(id);
			add(new ApplicationListView("application-list-item", getApplications()));
		}

	}

	private class ApplicationListView extends ListView<Application> {

		public ApplicationListView(String id, List<Application> applications) {
			super(id, applications);
		}

		@Override
		protected void populateItem(ListItem<Application> item) {
			Application application = item.getModelObject();
			item.add(new Label("application-name", application.getName()));
			List<Artifact> artifacts = application.getArtifacts();
			if (artifacts != null && artifacts.isEmpty()) {
				item.add(new ExternalLink("application-download-link", artifacts.get(0).getDownloadUrl().toString()));
			}
		}
	}
}
