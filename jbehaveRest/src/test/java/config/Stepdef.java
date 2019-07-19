package config;

import java.util.HashMap;
import java.util.Map;

import org.jbehave.core.annotations.*;
import org.junit.Assert;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;



public class Stepdef {
	/*
	RequestSpecification res = RestAssured.given();
	int code=201;
	int e_statuscode;
	@Given("a precondition")
	
	public void givenAPrecondition() {
	  // PENDING
		System.out.println("   rest assured ");
	// res.get("http://localhost:3000/employees");
	 
	}

	@When("a negative event occurs")
	
	public void whenANegativeEventOccurs() {
	  // PENDING
		
Map<String,String> data = new HashMap<String,String>();
		
		data.put("id", "11");
		data.put("first_name", "cognizant_india_11");
		data.put("last_name", "softvision_india_11");
		data.put("email", "softvision@cognizant.com");
		e_statuscode=res.given().contentType("application/json").body(data).when().post("http://localhost:3000/employees").getStatusCode();
		
		
	}

	@Then("a the outcome should be captured")
	
	public void thenATheOutcomeShouldBeCaptured() {
	  // PENDING
		
		Assert.assertEquals(code, e_statuscode);
		System.out.println("the status code is"+e_statuscode);
		System.out.println("done");
	}
	*/
	
	RequestSpecification res = RestAssured.given();
	int code=200;
	int e_statuscode;
	
	
	@Given("a precondition [uri]")
	public void givenAPreconditionuri(String uri) {
	  // PENDING
		res.get(uri);
		
	}

	@When("a negative event occurs")
	public void whenANegativeEventOccurs() {
		
		Map<String,String> data = new HashMap<String,String>();
		data.put("id", "7");
		data.put("first_name", "cognizant_india_7");
		data.put("last_name", "softvision_india_7");
		data.put("email", "softvision@cognizant.com");
		e_statuscode=res.given().contentType("application/json").body(data).when().post("http://localhost:3000/employees").getStatusCode();
		
		
		//e_statuscode=res.given().when().delete("http://localhost:3000/employees/11").getStatusCode();
	  // PENDING
	}

	@Then("a the outcome should be captured [date]")
	public void thenATheOutcomeShouldBeCaptureddate(String date) {
	  // PENDING
		
		Assert.assertEquals(code, e_statuscode);
		System.out.println("the status code is"+e_statuscode +"and date is "+ date);
		System.out.println("done");
		
	}

}
