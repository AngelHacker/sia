package com.sia.Email;

import java.io.IOException;

public class SendMailWithUserId {

	public boolean sendMail(String user, String emailId) {

		if(user==null||emailId==null)
			return false;
		
		user=user.toLowerCase();
		
		String body = "<p>Your userId is "+user;

		try {
			new SendMail().send(body);
			System.out.println("Sent mail with the userId "
					+ user);
			return true;
		} catch (IOException e) {
			
			e.printStackTrace();
			System.out.println("Mail sending failed");
		}
		return false;

	}
}
