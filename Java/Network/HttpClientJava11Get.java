package com.scripter;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

/**
 * @author Syn 
 * HTTPS GET ENDPOINT - https://reqres.in/api/users?page=1 
 * HTTP POST ENDPOINT - ?
 */
public class HttpCaller {
	public void makeHTTPcalls()  {
		HttpRequest httpRequest;
		try {
			httpRequest = HttpRequest.newBuilder()
					.uri(new URI("https://reqres.in/api/users?page=1"))
					.GET()
					.build();
			HttpClient client = HttpClient.newBuilder()
					.build();
			
			HttpResponse<String> httpResponse = client.send(httpRequest, BodyHandlers.ofString());
			System.out.println(httpResponse.statusCode());
			System.out.println(httpResponse.body());  
			
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
