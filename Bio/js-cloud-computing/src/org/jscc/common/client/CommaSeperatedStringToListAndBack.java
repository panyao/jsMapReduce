package org.jscc.common.client;

import java.util.HashSet;

public class CommaSeperatedStringToListAndBack {
	
	
	private final static String commaSeperatorChar = ",";
	
	
	public static HashSet<String> fromCommaSeperatedStringToList(String input) {
		
		return fromXSeperatedStringToList(input, commaSeperatorChar);
		
		
	}
	
	
	
	
	
	public static String fromSetOfStringsToCommaSeperatedString(
			HashSet<String> list) {
		
		
		
		
		return fromSetOfStringsToXSeperatedString(list, commaSeperatorChar);
	
		
		
	}
	
	
	
	
	public static HashSet<String> fromXSeperatedStringToList(
			String input,
			String seperatorChar) {
		
		String [] seperatedStuff = input.split(seperatorChar);
		
		HashSet<String> returnList = new HashSet<String>();
		
		
		for (int i = 0 ; i < seperatedStuff.length; i++) {
			
			returnList.add(seperatedStuff[i].trim());
			
			
		}
		
		return returnList;
		
		
		
		
	}
	
	
	
	
	
	public static String fromSetOfStringsToXSeperatedString(
			HashSet<String> set, 
			String seperatorChar) {
		
		
		StringBuffer sb = new StringBuffer();
		
		if (set == null) {
			return "";
		}
		
		
		
		for (int i = 0; i < set.size(); i++) {
			sb.append(set.toArray()[i]);
			
			
			//if it is not the last in line add a seperator
			if (i + 1 < set.size()) {
				
				sb.append(seperatorChar + " ");
			}
			
			
			
		}
		
		
		
		return sb.toString();
	
		
		
	}

}
