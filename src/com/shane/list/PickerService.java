package com.shane.list;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.Security;
import java.util.*;

//import javax.mail.*;
//import javax.mail.internet.*;
import javax.activation.*;

import com.sun.mail.smtp.SMTPTransport;







//import java.security.Security;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class PickerService {
	
	public ArrayList<Person> populateList(){
		File file = new File("C:\\shanergulley\\PersonListTest.txt");
		ArrayList<Person> personListGiver = new ArrayList<Person>();
		
		try {
			BufferedReader bufferReader = new BufferedReader(new FileReader(file));
			String currentLine = "";
			String currentFamily = "";
			String name = "";
			while ((currentLine = bufferReader.readLine()) != null) {
				if (currentLine.contains("!")) {
					currentFamily = currentLine.replace("!", "");
					
				} else if (currentLine.contains("?")) {
					name = currentLine.replace("?", "");
				} else if (currentLine.contains("@")) {
					String email = currentLine;
					Person person = new Person();
					person.setFamily(currentFamily);
					person.setEmail(email);
					person.setName(name);
					personListGiver.add(person);
					
					
				}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		System.out.print("loaded list");
		return personListGiver;
	}
	
	private void pick(ArrayList<Person> personListGiver) throws AddressException, MessagingException {
		
		ArrayList<Person> personListReciever = new ArrayList<Person>(personListGiver);
		Collections.shuffle(personListGiver);
		Collections.shuffle(personListReciever);
//		System.out.print(personListGiver.get(1).getFamily()+ personListGiver.get(1).getName());
		int retry = 0;
		boolean gulleyFamily = false;
		boolean counceFamily = false;
		boolean isValid = true;
		
	     for(int j=0; j < personListGiver.size(); j++)
	     {
	    	 String familyGiver = personListGiver.get(j).getFamily();
	    	 String familyReceiver = personListReciever.get(j).getFamily();
	    	 String nameGiver = personListGiver.get(j).getName();
	    	 String nameReceiver = personListReciever.get(j).getName();
	    	 if (!familyGiver.equals(familyReceiver))
	    	 {
	    		 if(gulleyFamily)
	    		 {	 
	    			 isValid = GulleyExceptions.exceptions(familyGiver, familyReceiver, nameGiver, nameReceiver);
	    		 }
	    		 
	    		 if(counceFamily)
	    		 {
	    			 isValid = GulleyExceptions.exceptions(familyGiver, familyReceiver, nameGiver, nameReceiver);
	    		 }
	    		 if(!isValid)
	    		 {
	    			 isValid = true;
	    			 retry++;
	    			 j=-1;
	    			 Collections.shuffle(personListGiver);
	    			 Collections.shuffle(personListReciever);	    			 	    				 
	    		 }

	    	 }else{
	    		 isValid = true;
	    		 retry++;
	    		 j=-1;
	    		 Collections.shuffle(personListGiver);
	    		 Collections.shuffle(personListReciever);
	    	 }
	     }
	     
	     System.out.println("Retry = "+ retry);
	     

		
		for(int j=0; j < personListGiver.size(); j++){
			String username = "";
			String password = "";
			String recipientEmail = personListGiver.get(j).getEmail();
			String title = personListGiver.get(j).getName()+" -open to see Secret Santa results!";
			String message = "Hello "+personListGiver.get(j).getName()+",\n"+
							 "    Congratulations! You have the honor of bringing joy to "+personListReciever.get(j).getName()+"!\n"+ 
					         "We are doing things a little different this Christmas.  For your person this year, you have the honor of:  \n" +
							 "1)praying for them on a consistent basis until Christmas\n"+
							 "2)coming up with a small gift or artwork or scrapbook page that shares a fun memory with that person or something that is encouraging\n"+
							 "or inspirational for them to use in the new year.\n"+
							 "Be creative! :)\n" +  
							 "\n"+
							 "Thank you\n"+
							 "Shane the train";
			//this.Send(username, password, recipientEmail, "", title, message);
			Send send = new Send();
			send.Send(username, password, recipientEmail, "", title, message);		
			
			//System.out.print(personListGiver.get(j).getName()+","+personListGiver.get(j).getFamily() +"="+personListReciever.get(j).getName()+","+personListReciever.get(j).getFamily() + "\n");
			
		}
		
	}
	
	/**
     * Send email using GMail SMTP server.
     *
     * @param username GMail username
     * @param password GMail password
     * @param recipientEmail TO recipient
     * @param ccEmail CC recipient. Can be empty if there is no CC recipient
     * @param title title of the message
     * @param message message to be sent
     * @throws AddressException if the email address parse failed
     * @throws MessagingException if the connection is dead or not in the connected state or if the message is not a MimeMessage
     */
//    public static void Send(final String username, final String password, String recipientEmail, String ccEmail, String title, String message) throws AddressException, MessagingException {
//        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
//        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
//
//        // Get a Properties object
//        Properties props = System.getProperties();
//        props.setProperty("mail.smtps.host", "smtp.gmail.com");
//        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
//        props.setProperty("mail.smtp.socketFactory.fallback", "false");
//        props.setProperty("mail.smtp.port", "465");
//        props.setProperty("mail.smtp.socketFactory.port", "465");
//        props.setProperty("mail.smtps.auth", "true");
//
//        /*
//        If set to false, the QUIT command is sent and the connection is immediately closed. If set 
//        to true (the default), causes the transport to wait for the response to the QUIT command.
//
//        ref :   http://java.sun.com/products/javamail/javadocs/com/sun/mail/smtp/package-summary.html
//                http://forum.java.sun.com/thread.jspa?threadID=5205249
//                smtpsend.java - demo program from javamail
//        */
//        props.put("mail.smtps.quitwait", "false");
//
//        Session session = Session.getInstance(props, null);
//
//        // -- Create a new message --
//        final MimeMessage msg = new MimeMessage(session);
//
//        // -- Set the FROM and TO fields --
//        msg.setFrom(new InternetAddress(username));
//        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail, false));
//
//        if (ccEmail.length() > 0) {
//            msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccEmail, false));
//        }
//
//        msg.setSubject(title);
//        msg.setText(message, "utf-8");
//        msg.setSentDate(new Date());
//
//        SMTPTransport t = (SMTPTransport)session.getTransport("smtps");
//
//        t.connect("smtp.gmail.com", username, password);
//        t.sendMessage(msg, msg.getAllRecipients());      
//        t.close();
//    }
//
	
	public static void main(String[] args) throws AddressException, MessagingException{
		
		PickerService pickerService = new PickerService();
		ArrayList<Person> personListGiver = pickerService.populateList();
		
		pickerService.pick(personListGiver);
		
	}

}
