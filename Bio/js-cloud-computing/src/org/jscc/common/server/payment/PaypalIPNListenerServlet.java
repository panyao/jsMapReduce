package org.jscc.common.server.payment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jscc.common.server.ServletUtils;

import com.google.inject.Singleton;


/**
 * similar to IPNGuide.pdf
 * 
 * @author ra
 *
 */
@Singleton
public class PaypalIPNListenerServlet extends HttpServlet {
	

	private static final Logger log = Logger.getLogger(PaypalIPNListenerServlet.class
			.getName());

	public void doGet(HttpServletRequest request, HttpServletResponse response) {

		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");

		} catch (UnsupportedEncodingException e1) {
			log.severe(e1.getMessage());
		}

		
		
		try {
			Enumeration en = request.getParameterNames();
			
			StringBuffer sb = new StringBuffer();
			sb.append("cmd=_notify-validate");
			
			while (en.hasMoreElements()) {
				String paramName = (String) en.nextElement();
				String paramValue = request.getParameter(paramName);
				sb.append("&" + paramName + "=" + ServletUtils.urlEncode(paramValue));
			}
			
			/* Extract form data */
			URL u = new URL("http://www.paypal.com/cgi-bin/webscr") ;
			URLConnection uc = u.openConnection();
			uc.setDoOutput(true);
			uc.setRequestProperty("Content-Type", "application/x-www-form-urlencoded" ) ;
			PrintWriter pw = new PrintWriter(uc.getOutputStream());
			pw.println(sb.toString());
			pw.close();
			
			BufferedReader in = new BufferedReader(
					new InputStreamReader(uc.getInputStream()));
			
			String ipnres = in.readLine();
			
			in.close();

			if (ipnres.equals("VERIFIED" )) {
				
				
			    // Check the payment_status is Completed 
			    // Check that txn_id has not been previously processed 
			    // Check that receiver_email is your Primary PayPal email 
			    // Check that payment_amount/payment_currency are correct 
			    // Process payment 
				
				

			} else {
				
			    // If 'INVALID' TODO: Log for manual investigation. 
				
			}
		} catch (MalformedURLException e) {
			log.severe(e.getMessage());
		} catch (IOException e) {
			log.severe(e.getMessage());
		}
		
		
		
//		<?php 
//				error_reporting(E_ALL ^ E_NOTICE); 
//				$email = $_GET['ipn_email']; 
//				$header = ""; 
//				$emailtext = ""; 
//				 
//				// Read the post from PayPal and add 'cmd' 
//				$req = 'cmd=_notify-validate'; 
//				if(function_exists('get_magic_quotes_gpc')) 
//				  { $get_magic_quotes_exits = true;} 
//				foreach ($_POST as $key => $value) 
//				  // Handle escape characters, which depends on setting of magic quotes 
//				  { if($get_magic_quotes_exists == true && get_magic_quotes_gpc() == 1) 
//				    {  $value = urlencode(stripslashes($value)); 
//				  } else { 
//				    $value = urlencode($value); 
//				  }  
//				  $req .= "&$key=$value";  
//				} 
//				// Post back to PayPal to validate 
//				$header .= "POST /cgi-bin/webscr HTTP/1.0\r\n"; 
//				$header .= "Content-Type: application/x-www-form-urlencoded\r\n"; 
//				$header .= "Content-Length: " . strlen($req) . "\r\n\r\n"; 
//				$fp = fsockopen ('www.paypal.com', 80, $errno, $errstr, 30); 
//				 
//				// Process validation from PayPal 
//				if (!$fp) { // HTTP ERROR  
//				} else { 
//				// NO HTTP ERROR  
//				fputs ($fp, $header . $req); 
//				while (!feof($fp)) { 
//				  $res = fgets ($fp, 1024); 
//				  if (strcmp ($res, "VERIFIED") == 0) { 
//				    // TODO: 
//				    // Check the payment_status is Completed 
//				    // Check that txn_id has not been previously processed 
//				    // Check that receiver_email is your Primary PayPal email 
//				    // Check that payment_amount/payment_currency are correct 
//				    // Process payment 
//				     
//				    // If 'VERIFIED', send an email of IPN variables and values to the 
//				    // specified email address 
//				    foreach ($_POST as $key => $value){ 
//				      $emailtext .= $key . " = " .$value ."\n\n"; 
//				    } 
//				    mail($email, "Live-VERIFIED IPN", $emailtext . "\n\n" . $req); 
//				  } else if (strcmp ($res, "INVALID") == 0) { 
//				    // If 'INVALID', send an email. TODO: Log for manual investigation. 
//				    foreach ($_POST as $key => $value){ 
//				      $emailtext .= $key . " = " .$value ."\n\n"; 
//				    } 
//				    mail($email, "Live-INVALID IPN", $emailtext . "\n\n" . $req); 
//				  } 
//				} 
//				fclose ($fp); 
//				?> 
//		

	}

}