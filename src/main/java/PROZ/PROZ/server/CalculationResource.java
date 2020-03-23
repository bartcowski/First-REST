package PROZ.PROZ.server;

import javax.script.*;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import PROZ.PROZ.client.Calculation;

@Path("/app")
public class CalculationResource {
	
	@GET
	@Path("/calc")
	@Produces(MediaType.TEXT_PLAIN)
	public String getExample() {
		return "123";
	}
	
	@POST
	@Path("/calculator")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Calculation doCalculations(Calculation calc) {
		ScriptEngineManager calcEngineManager = new ScriptEngineManager(null);
		ScriptEngine calcEngine = calcEngineManager.getEngineByName("JavaScript");
		String res = calc.getExpression();
		
		try {
			Object result = calcEngine.eval(res);
			res = result.toString();
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		calc.setResult(res);
		return calc;	
	}
}
