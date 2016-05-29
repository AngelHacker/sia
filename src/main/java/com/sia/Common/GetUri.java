package com.sia.Common;

import java.net.URI;
import javax.ws.rs.core.UriBuilder;


public class GetUri {

	public static URI getUri(String url){
		return UriBuilder.fromUri(url).build();
	}
}
