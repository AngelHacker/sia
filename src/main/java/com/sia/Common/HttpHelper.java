package com.sia.Common;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import org.glassfish.jersey.client.ClientConfig;
import java.net.URI;

public class HttpHelper {

	public String getHttpResponse(URI url) throws Exception{
		int retryTime =3;
		for(int retry = 0; retry < retryTime; retry++){
			try{
				ClientConfig config = new ClientConfig();
				Client client = ClientBuilder.newClient(config);
				WebTarget target = client.target(url);
				String str = target.request().accept(MediaType.APPLICATION_JSON)
						.get(String.class);
				return str;				
			}
			catch(Exception ex){
				System.out.println("Exception " + ex.toString() + " occured while calling url " + url.getPath());
			}
		}		
		
		throw new Exception("Failed to fetch data after " + retryTime + " attempts");
	}
}
