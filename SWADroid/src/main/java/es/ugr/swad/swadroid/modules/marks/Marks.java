/**
 * Information module for get courses's information
 *
 * @author Jose Antonio Guerrero Aviles <cany20@gmail.com>
 */

package es.ugr.swad.swadroid.modules.marks;

import android.os.Bundle;
import android.webkit.WebView;

import es.ugr.swad.swadroid.Constants;
import es.ugr.swad.swadroid.R;
import es.ugr.swad.swadroid.analytics.SWADroidTracker;
import es.ugr.swad.swadroid.gui.MenuActivity;
import es.ugr.swad.swadroid.gui.WebViewFactory;
import es.ugr.swad.swadroid.modules.courses.Courses;
import es.ugr.swad.swadroid.utils.Utils;

/**
 * Marks module for show user's marks
 *
 * @author Juan Miguel Boyero Corral <juanmi1982@gmail.com>
 */
public class Marks extends MenuActivity {

	private static final String TAG = Constants.APP_TAG + " Marks";

	/**
	 * Webview to show marks
	 */
    private WebView webview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview_information_screen_layout);
		
		webview = (WebView) this.findViewById(R.id.info_webview_dialog);

		getSupportActionBar().setSubtitle(Courses.getSelectedCourseShortName());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		setTitle(R.string.marksModuleLabel);
	}

    @Override
    protected void onStart() {
        super.onStart();

        SWADroidTracker.sendScreenView(getApplicationContext(), TAG);

        String content = this.getIntent().getStringExtra("content");

        content = Utils.fixLinks(content);
        if (content.startsWith("<![CDATA[")) {
            content = content.substring(9, content.length() - 3);
        }

        webview = WebViewFactory.getMathJaxWebView(webview);
        webview.setWebViewClient(WebViewFactory.getMathJaxExpression(content));
    }
}