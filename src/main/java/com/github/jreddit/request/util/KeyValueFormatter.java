package com.github.jreddit.request.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KeyValueFormatter {

	/** Logger for this class. */
	final static Logger LOGGER = LoggerFactory.getLogger(KeyValueFormatter.class);
	
	/**
	 * Format a mapping of key-value pairs to a string, separating key from value using an
	 * equals (=) sign, and separating pairs using an ampersand (&) sign.
	 * 
	 * @param keyValueParameters Mapping of key-value pairs
	 * @param encodeUTF8 Whether or not it should be encoded in UTF-8
	 * 
	 * @return Formatted string of key-value pairs (e.g. "a=1&b=something")
	 */
	public static String format(Map<String, String> keyValueParameters, boolean encodeUTF8) {
		
		// Key set
		Set<String> keys = keyValueParameters.keySet();
		
		// Iterate over keys
		String paramsString = "";
		boolean start = true;
		for (String key : keys) {
			
			// Add separation ampersand
			if (!start) {
				paramsString = paramsString.concat("&");
			} else {
				start = false;
			}
			
			// Retrieve value
			String value = keyValueParameters.get(key);
			
			// Encode key
			if (encodeUTF8) {
				try {
					value = URLEncoder.encode(value, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					LOGGER.warn("Unsupported Encoding Exception thrown when encoding value", e);
				}
			}
			
			// Add key-value pair
			paramsString = paramsString.concat(key + "=" + value);

		}
		
		// Return final parameter string
		return paramsString;
		
	}
	
	public static String formatCommaSeparatedList(List<String> list) {
		String stringList = "";
		for (String s : list) {
			stringList += s + ",";
		}
		if (stringList.length() > 0) {
			stringList = stringList.substring(0, stringList.length() - 1);
		}
		return stringList;
	}
	
}
