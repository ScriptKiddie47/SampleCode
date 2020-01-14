package Rebinq.com.RestAuth;

import java.io.IOException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws IOException {

		universalhttpGETCollectionExample ();
		//basic_non_preemtive_httpGETCollectionExample();
		// basichttpGETCollectionExample();
	}

	private static void basichttpGETCollectionExample() {
		ClientConfig clientConfig = new ClientConfig();

		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("howtodoinjava", "password");
		clientConfig.register(feature);

		// clientConfig.register(JacksonFeature.class);

		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget webTarget = client.target("https://reqbin.com/echo/post/json");

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();

		System.out.println(response.getStatus());
		System.out.println(response.getStatusInfo());

		
	}

	private static void basic_non_preemtive_httpGETCollectionExample() {
		ClientConfig clientConfig = new ClientConfig();

		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basicBuilder().nonPreemptive()
				.credentials("username", "password").build();

		final Client client = ClientBuilder.newClient();
		client.register(feature);
		WebTarget webTarget = client.target("https://reqbin.com");

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();

		System.out.println(response.getStatus());
		System.out.println(response.getStatusInfo());

	}
	
	private static void universalhttpGETCollectionExample() {
		//HttpAuthenticationFeature feature = HttpAuthenticationFeature.universal("username", "password");
		 
		//Universal builder having different credentials for different schemes
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.universalBuilder()
		                .credentialsForBasic("username1", "password1")
		                .credentials("username2", "password2").build();
		 
		final Client client = ClientBuilder.newClient();
		client.register(feature);

		WebTarget webTarget = client.target("https://reqbin.com");

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();

		System.out.println(response.getStatus());
		System.out.println(response.getStatusInfo());

	}
	
}
