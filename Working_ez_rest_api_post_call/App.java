package ScriptKiddie.ScriptKidderExclude;

import java.io.IOException;
import java.util.Iterator;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		// Create a client
		Client client = ClientBuilder.newClient();
		// Configure our client based on our requirement
		// Set a target to Client
		WebTarget target = client.target("http://dummy.restapiexample.com/api/v1/create");
		String input = "{\"name\":\"Ritam\",\"salary\":\"123\",\"age\":\"23\"}";
		
		String res =  target.request().post(Entity.entity(input, MediaType.APPLICATION_JSON), String.class);
		
		System.out.println("res:"+res);
		//Jackson
		ObjectMapper mapper = new ObjectMapper();
		//mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		//Staff staff2 = mapper.readValue(jsonInString, Staff.class);
		Employee employee = new Employee();
		try {
			employee = mapper.readValue(res,Employee.class);
			System.out.println("EmployoPOJOObject:"+employee);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Employee ID:"+employee.getData().getId());
		System.out.println("Employee Name:"+employee.getData().getName());
	}
}
