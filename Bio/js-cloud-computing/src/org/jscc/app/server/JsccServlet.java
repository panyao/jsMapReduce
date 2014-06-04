package org.jscc.app.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jscc.common.server.ServletUtils;

import com.google.inject.Singleton;

@Singleton
public class JsccServlet extends HttpServlet {

	private static final long serialVersionUID = -4439494609468870407L;
	
	// 'Logger' is a java class used to take error messages and log them.
	// 'Logger' needs a name, preferably the class it is called upon.
	private static final Logger log = Logger.getLogger(
			JsccServlet.class.getName());
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			log.severe(e1.getMessage());
		}

		try {
			PrintWriter pw;
			BufferedWriter bw;
			pw = response.getWriter();
			bw = new BufferedWriter(pw);
			String titleString = "js-cloudcomputing-testbed";

			bw.write(ServletUtils.getGWTHeader(request, titleString,
					"/gwtapp/gwtapp.nocache.js",
					"/images/TODO.ico"));
			
			bw.write(ServletUtils.endBodyHTMLWithHistoryAndFeedbackSupport);
			bw.close();
			pw.close();
		} catch (IOException e) {
			log.severe(e.getMessage());
		}
	}

}