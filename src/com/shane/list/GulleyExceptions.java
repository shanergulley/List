package com.shane.list;

import java.util.Collections;

public class GulleyExceptions {
	public static boolean exceptions(String familyGiver, String familyReceiver, String nameGiver, String nameReceiver)
	{
		boolean isValid = true;
		
		 if(nameGiver.trim().equalsIgnoreCase("") && !nameReceiver.trim().equalsIgnoreCase(""))
		 {
			 isValid = false;
		 }
		 if(nameGiver.trim().equalsIgnoreCase("") && nameReceiver.trim().equalsIgnoreCase(""))
		 {
			 isValid = false;
		 }
		 if(nameGiver.trim().equalsIgnoreCase("") && nameReceiver.trim().equalsIgnoreCase(""))
		 {
			 isValid = false;
		 }
				
		return isValid;
	}

}
