package fr.eni.encheres;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;


public abstract class CookieUtils {
	
	
	/**
	 * 
	 * @param message
	 * @return 
	 * @throws UnsupportedEncodingException 
	 */
	public static Cookie SetCookie(String nomCookie,String message , int duration) throws UnsupportedEncodingException {
		
		
		Cookie cookie = new Cookie(nomCookie, URLEncoder.encode( message, "UTF-8" )); 
		cookie.setMaxAge(duration);	
		
		
		return cookie;
		
	}
	
	public static String readCookie(String nomCookie, Cookie[] cookies) throws UnsupportedEncodingException {
		
		
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        if (cookie.getName().equals(nomCookie)) {
		        	
		        	return URLDecoder.decode( cookie.getValue(), "UTF-8" );     	
		        	
		         
		        }
		    }
		}
		return null;
		
		
		
		
		
	}

}
