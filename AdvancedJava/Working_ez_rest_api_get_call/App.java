package ScriptKiddie.ScriptKidderExclude;

import java.io.IOException;
import java.util.Iterator;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonParseException;
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
		WebTarget target = client.target("http://dummy.restapiexample.com/api/v1/employees");
		System.out.println(target.request(MediaType.TEXT_HTML).get(String.class));
		String JSONData = target.request(MediaType.TEXT_HTML).get(String.class);

		
		//Jackson
		ObjectMapper mapper = new ObjectMapper();
		//Staff staff2 = mapper.readValue(jsonInString, Staff.class);
		EmployeePOJO employeePOJO = new EmployeePOJO();
		try {
			employeePOJO = mapper.readValue(JSONData,EmployeePOJO.class);
			System.out.println("EmployoPOJOObject:"+employeePOJO);
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
		
		System.out.println("Status:"+employeePOJO.getStatus());
		for(int i = 0; i<employeePOJO.getData().size();i++) {
			System.out.println("ID:"+employeePOJO.getData().get(i).getId());
			System.out.println("Employee name:"+employeePOJO.getData().get(i).getEmployeeName());
			System.out.println("Salary::"+employeePOJO.getData().get(i).getEmployeeSalary());
			System.out.println("Age:"+employeePOJO.getData().get(i).getEmployeeAge());
			System.out.println();
		}
	}
}
