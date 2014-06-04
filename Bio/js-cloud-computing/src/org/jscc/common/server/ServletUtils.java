package org.jscc.common.server;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

public class ServletUtils {
	
	private static final String LOCALHOST_ADDRESS = "127.0.0.1";
	

	//cache stuff
	
	private static final Logger log = Logger.getLogger(ServletUtils.class.getName());
	
	public static final String endBodyHTMLWithHistoryAndFeedbackSupport =

	"<iframe src=\"javascript:''\" id=\"__gwt_historyFrame\" tabIndex='-1' style=\"position:absolute;width:0;height:0;border:0\"></iframe>"
	+ "<div id=\"feedback-button\">"
	+ "</div>"
	+ "</body>"
	+ "</html>";
	


	
	
	public static final String urlEncode(String string) {
		
		String returnString = null;
		try {
			returnString = URLEncoder.encode(string, "UTF-8");
		} catch (UnsupportedEncodingException e) {

			log.severe(e.getMessage());
		}
		
		return returnString;
		
		
	}
	
	public static final String urlDecode(String string) {
		String returnString = null;
		try {
			returnString = URLDecoder.decode(string, "UTF-8");
		} catch (UnsupportedEncodingException e) {

			log.severe(e.getMessage());
		}
		return returnString;
		
	}
	public static final String bodyHTML = "<body>"
			+ endBodyHTMLWithHistoryAndFeedbackSupport;
	
	public static String getFullPathOfThisServerWithoutSlashAtTheEnd(HttpServletRequest request) {

		
		//detect https request for instance https://scisurfer.appspot.com
		if (request.getServerPort() == 443) {
			return "https://" + request.getServerName();
			
		//development server:
		} else if (request.getServerPort() == 8888) {

			return "http://"
				+ request.getServerName() 
				+ ":" 
				+ request.getServerPort();
			
		} else {

			return 	
				"http://" + request.getServerName();

		}


	}
	
	

	
	
	public static String getGWTHeader(
			HttpServletRequest request, 
			String title, 
			String relativePathToGWT,
			String relativePathToFavicon) {

		String fullPathOfServer = getFullPathOfThisServerWithoutSlashAtTheEnd(request);

		
		

		return

		//"<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">"
		"<!DOCTYPE html>"
				+ "<html>"
				+ "<head>"
				+ "<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">"
				+ "<meta name=\"gwt:property\" content=\"locale=" 
						+ request.getLocale().getLanguage() + "\">"
				
				+"<link rel=\"shortcut icon\" href=\""+relativePathToFavicon+"\" type=\"image/vnd.microsoft.icon\" />"
				+"<link rel=\"icon\" href=\""+relativePathToFavicon+"\" type=\"image/vnd.microsoft.icon\" />"

				

				+ "<link type=\"text/css\" rel=\"stylesheet\" href=\""

				+ fullPathOfServer
				+ "/stylesheet.css\">"

				+ "<title>"

				+ title

				+ "</title>"
				// GWT stuff:
				+ " <script type=\"text/javascript\" src=\""
				+ fullPathOfServer + relativePathToGWT + "\"></script>"
				+ "</head>"
				+ "<body>";

	}

	
	

	

}
