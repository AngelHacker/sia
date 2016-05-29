package com.sia.Email;

import com.amazonaws.services.simpleemail.*;
import com.amazonaws.services.simpleemail.model.*;
import com.amazonaws.regions.*;

import java.io.IOException;

public class SendMail {

	static final String FROM = "vivek15kv@gmail.com";
	static final String TO = "vivek15kv@gmail.com";
	static final String HTML_BODY = "<a href=\"www.google.com\">Click here</a>";
	static final String TEXT_BODY ="Sending mails from your account. Your account has been hacked. Beware dude!";
    static final String SUBJECT = "Account hacked(vickysddfr@gmail.com).Beware!";
    public static String send(String rawBody) throws IOException {    	
        
        // Construct an object to contain the recipient address.
        Destination destination = new Destination().withToAddresses(new String[]{TO});
        
        // Create the subject and body of the message.
        Content subject = new Content().withData(SUBJECT);
        Content textBody = new Content().withData(TEXT_BODY);
        Content htmlBody = new Content().withData(rawBody);
        Body body = new Body().withHtml(htmlBody).withText(textBody);
        
        // Create a message with the specified subject and body.
        Message message = new Message().withSubject(subject).withBody(body);
        
        // Assemble the email.
        SendEmailRequest request = new SendEmailRequest().withSource(FROM).withDestination(destination).withMessage(message);
        
        try
        {        
            //System.out.println("Attempting to send an email through Amazon SES by using the AWS SDK for Java...");
        
            // Instantiate an Amazon SES client, which will make the service call. The service call requires your AWS credentials. 
            // Because we're not providing an argument when instantiating the client, the SDK will attempt to find your AWS credentials 
            // using the default credential provider chain. The first place the chain looks for the credentials is in environment variables 
            // AWS_ACCESS_KEY_ID and AWS_SECRET_KEY. 
            // For more information, see http://docs.aws.amazon.com/AWSSdkDocsJava/latest/DeveloperGuide/credentials.html
            AmazonSimpleEmailServiceClient client = new AmazonSimpleEmailServiceClient();
               
            // Choose the AWS region of the Amazon SES endpoint you want to connect to. Note that your sandbox 
            // status, sending limits, and Amazon SES identity-related settings are specific to a given AWS 
            // region, so be sure to select an AWS region in which you set up Amazon SES. Here, we are using 
            // the US West (Oregon) region. Examples of other regions that Amazon SES supports are US_EAST_1 
            // and EU_WEST_1. For a complete list, see http://docs.aws.amazon.com/ses/latest/DeveloperGuide/regions.html 
            Region REGION = Region.getRegion(Regions.US_EAST_1);
            client.setRegion(REGION);
       
            // Send the email.
            client.sendEmail(request);  
            System.out.println("Email sent!");
            return "Email sent!";
        }
        catch (Exception ex) 
        {
            System.out.println("The email was not sent.");
            System.out.println("Error message: " + ex.getMessage());
        }
        
        return "Email was not sent";
    }
}
