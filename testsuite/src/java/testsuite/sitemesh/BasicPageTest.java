package testsuite.sitemesh;

import java.io.IOException;
import java.net.MalformedURLException;

import org.xml.sax.SAXException;

import com.meterware.httpunit.WebResponse;
import com.meterware.httpunit.HttpInternalErrorException;
import com.meterware.httpunit.HttpNotFoundException;
import testsuite.tester.WebTest;

/**
 * Test basic capabilities of web-app - no sitemesh specific stuff.
 *
 * @author <a href="mailto:joe@truemesh.com">Joe Walnes</a>
 */
public class BasicPageTest extends WebTest {

	/**
	 * Check plain page is working and contains correct title.
	 */
	public void testPage() throws Exception {
		WebResponse rs = wc.getResponse( server.getBaseURL() + "/basic/page.jsp" );
		assertEquals( 200, rs.getResponseCode() );
		assertEquals( "SiteMesh plain page", rs.getTitle() );
	}

	/**
	 * Check error 500 is properly sent.
	 */
	public void testError() throws Exception {
		try {
			WebResponse rs = wc.getResponse( server.getBaseURL() + "/basic/error-500.jsp" );
			fail( "Expected 500" );
		}
		catch ( HttpInternalErrorException e ) {
		}
	}

	/**
	 * Check error 404 is properly sent.
	 */
	public void testNotFound() throws Exception {
		try {
			WebResponse rs = wc.getResponse( server.getBaseURL() + "/basic/dfddgdfvdf.jsp" );
			fail( "Expected 404" );
		}
		catch ( HttpNotFoundException e ) {
		}
	}

    public void testNonHtml() throws Exception {
        WebResponse rs = wc.getResponse( server.getBaseURL() + "/basic/text.jsp" );
        assertEquals("This is a plain page.", rs.getText().trim());
        assertEquals("text/plain", rs.getContentType());

    }

}