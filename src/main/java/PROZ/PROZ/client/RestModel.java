package PROZ.PROZ.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RestModel {
	private Calculation calc;
	private Client client;
	private WebTarget webTarget;
	private Gson gson;
	private Response response;
	
	final private String URL = "http://localhost:8080/PROZ/restapp/app/calculator";
	
	public String calculate(String input) {
		calc = new Calculation(input, "");
		gson = new GsonBuilder().create();
		
		client = ClientBuilder.newClient();
		webTarget = client.target(URL);
		response = webTarget.request(MediaType.APPLICATION_JSON)
							.post(Entity.json(gson.toJson(calc)));
		
		String responseString = response.readEntity(String.class);
		calc = gson.fromJson(responseString, Calculation.class);
		response.close();
		
		return calc.getResult();
	}
}
