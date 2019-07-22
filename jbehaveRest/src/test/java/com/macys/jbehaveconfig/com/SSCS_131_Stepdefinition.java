package com.macys.jbehaveconfig.com;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;

import org.jbehave.core.annotations.*;
import org.junit.Assert;

import com.databaseconnection.mcys.com.DatabaseConnection;

import io.restassured.RestAssured;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class SSCS_131_Stepdefinition {

	DatabaseConnection obj=new DatabaseConnection();
	RequestSpecification req = RestAssured.given();

	int db_result=0;
	String db_query;
	String uri;
	int status_code=0;
	int rowcount_before=0;
	int rowcount_after=0;
	static Logger logger= Logger.getLogger(SSCS_131_Stepdefinition.class);
	
	//Scenario: Adding 100 rows to the empty Bins
	
	@Given("a Storage Location room[uri] is defined,there are zero bins defined")
	
	public void givenAStorageLocationRoomuriIsDefinedthereAreZeroBinsDefined(String uri) throws SQLException {
	 		
		
		
			int initial_bin=0;
			req.get(uri);
			logger.info("API requset has been sent");
			db_query="select count(*) from bin;";
			db_result=obj.bdconnection( db_query);
			System.out.println("The initial number of records are "+ db_result );
			logger.info("Getting records to check whether bin has zero records");

	}

	@When("user passes [user_id],room_id and number of [bins] to add as 100")
	
	public void whenUserPassesuser_idroom_idAndNumberOfbinsToAddAs100(String userid,String rows) throws SQLException {
	 
		logger.info("posting data to add 100 bins");
		Map<String,String> data= new HashMap<String,String>();
		data.put("userid", userid);
		data.put("bins", rows);
		req.given().contentType("application/json").body(data).when().post(uri).then().assertThat().statusCode(201);


	}

	@Then("the system will insert 100 rows/bins into the BIN table")
	
	public void thenTheSystemWillInsert100RowsbinsIntoTheBINTable(String userid,String rows) throws SQLException {
	  
		db_query="select count(*) from bin";
		db_result=obj.bdconnection( db_query);
		Assert.assertEquals(100, db_result);
		logger.info("checking whether 100 bins have created or not in database");


		
	}

	@Then("pass a message back indicating that 100 bins/rows are defined")
	
	public void thenPassAMessageBackIndicatingThat100BinsrowsAreDefined() throws SQLException {
	  
		ResponseBody body=req.given().when().post().getBody();

		String msg_response=body.asString();
		String expected_msg="";
		Assert.assertEquals(expected_msg, msg_response);
		logger.info("Verifying message after inserting 100 rows");


		
	}
	
	//Scenario: Updating X rows to the existing Bins

	@Given("a Storage Location room[uri] is defined,there are bins 1 through 10 defined")
	public void givenAStorageLocationRoomuriIsDefinedthereAreBins1Through10Defined() throws SQLException {

		req.get(uri);
		logger.info("API request has been sent");
		db_query="select count(*) from bin;";
		db_result=obj.bdconnection( db_query);
		System.out.println("The initial number of records are "+ db_result );
		logger.info("checking number of bins in the database");

	}

	@When("user passes user_id, room_id and number of bins to add as x number")
	public void whenUserPassesUser_idRoom_idAndNumberOfBinsToAddAsXNumber(String userid,String rows) {

		Map<String,String> data= new HashMap<String,String>();
		data.put("userid", userid);
		data.put("bins", rows);
		req.given().contentType("application/json").body(data).when().post(uri).then().assertThat().statusCode(201);
		logger.info("posting data through API");

	}

	@Then("the system will insert X rows into the BIN table starting with Bin 11")
	public void thenTheSystemWillInsertXRowsIntoTheBINTableStartingWithBin11() throws SQLException {

		db_query="select count(*) from bin;";
		db_result=obj.bdconnection( db_query);
		System.out.println("Now total number of records are initial + added "+ db_result );
		logger.info("checking bin inserted from 11th row");

	}

	@Then("pass a message back indicating that 10+x number of bins are defined")

	public void thenPassAMessageBackIndicatingThat10xNumberOfBinsAreDefined() {

		logger.info("checking for the message after inserting");
		ResponseBody body=req.given().when().post().getBody();
		String msg_response=body.asString();
		String expected_msg="";
		Assert.assertEquals(expected_msg, msg_response);


	}


	// Scenario: Verify that user adding less than 1 bin

	@Given("a Storage Location room [uri]information is defined")
	public void givenAStorageLocationRoomuriinformationIsDefined() {

		logger.info("API requset has been sent");
		req.get(uri);


	}

	@When("user passes [user_id], room_id and number of [bins] to add as less than 1 bin")
	public void whenUserPassesuser_idRoom_idAndNumberOfbinsToAddAsLessThan1Bin(String userid,String rows) {

		logger.info("Posting data through API");
		Map<String,String> data= new HashMap<String,String>();
		data.put("userid", userid);
		data.put("bins", rows);
		req.given().contentType("application/json").body(data).when().post(uri).then().assertThat().statusCode(201);


	}

	@Then("the system will not insert any rows into the BIN table")
	public void thenTheSystemWillNotInsertAnyRowsIntoTheBINTable() throws SQLException {
		// PENDING
		logger.info("checking whether any rows are inserted or not");
		db_query="select count(*) from bin;";
		db_result=obj.bdconnection(db_query);
		System.out.println("Now total number of records are "+ db_result );

	}

	@Then("pass a error message back saying that number of bins to add must be a number greater than zero.")
	public void thenPassAErrorMessageBackSayingThatNumberOfBinsToAddMustBeANumberGreaterThanZero() {

		logger.info("checking for the message after posting");
		ResponseBody body=req.given().when().post().getBody();
		String msg_response=body.asString();
		String expected_msg="";
		Assert.assertEquals(expected_msg, msg_response);


	}

	// Scenario: Verify user-id is required to create bins


	@Given("a Storage Location room [uri]details is defined")
	public void givenAStorageLocationRoomuridetailsIsDefined() throws SQLException {

		logger.info("API requset has been sent");
		req.get(uri);
		logger.info("check for the number of records before posting any data");
		db_query="select count(*) from bin;";
		db_result=obj.bdconnection(db_query);
		rowcount_before=db_result;
		System.out.println("Now total number of records before passing invalid userid "+ db_result );

	}

	@When("user doesn't pass a user_id but every other input data [bin]")
	public void whenUserDoesntPassAUser_idButEveryOtherInputDatabin(String rows) {

		logger.info("Verifying post method with username parameter as null");
		Map<String,String> data= new HashMap<String,String>();
		data.put("userid","" );
		data.put("bins", rows);
		req.given().contentType("application/json").body(data).when().post(uri).then().assertThat().statusCode(201);


	}

	@Then("the system will not insert any input data into the BIN table")
	public void thenTheSystemWillNotInsertAnyInputDataIntoTheBINTable() throws SQLException {

		logger.info("checking whether any row has been inserted or not");
		db_query="select count(*) from bin;";
		db_result=obj.bdconnection( db_query);
		rowcount_after=db_result;
		System.out.println("Now total number of records after passing invalid userid "+ db_result );
		Assert.assertEquals(rowcount_before,rowcount_after);


	}

	@Then("pass a error message back saying that user_id is required")
	public void thenPassAErrorMessageBackSayingThatUser_idIsRequired() {
		logger.info("Verifying message after posting ");
		ResponseBody body=req.given().when().post().getBody();
		String msg_response=body.asString();
		String expected_msg="";
		Assert.assertEquals(expected_msg, msg_response);

	}


	//Scenario: Verifying for invalid room_id

	@Given("a user storage location[uri] room details")
	public void givenAUserStorageLocationuriRoomDetails() throws SQLException {

		logger.info("API request has been sent");
		req.get(uri);//enter wrong roomid
		logger.info("check for the number of records before posting any data");
		db_query="select count(*) from bin;";
		db_result=obj.bdconnection(db_query);
		rowcount_before=db_result;
		System.out.println("Now total number of records before passing invalid userid "+ db_result );

	}

	@When("user doesn't pass a valid room_id  but every other input data [bin],[userid]")
	public void whenUserDoesntPassAValidRoom_idButEveryOtherInputDatabinuserid(String rows,String userid) {
		logger.info("Posting data through API");
		Map<String,String> data= new HashMap<String,String>();
		data.put("userid",userid );
		data.put("bins", rows);
		req.given().contentType("application/json").body(data).when().post(uri).then().assertThat().statusCode(201);



	}

	@Then("the system will not add any rows into the BIN table")
	public void thenTheSystemWillNotAddAnyRowsIntoTheBINTable() throws SQLException {

		logger.info("checking whether any row has been inserted or not");
		db_query="select count(*) from bin;";
		db_result=obj.bdconnection(db_query);
		rowcount_before=db_result;
		System.out.println("Now total number of records before passing invalid userid "+ db_result );
		Assert.assertEquals(rowcount_before,rowcount_after);
	}

	@Then("pass a error message back saying that a valid room_id is required")
	public void thenPassAErrorMessageBackSayingThatAValidRoom_idIsRequired() {
		logger.info("verifying the result message");
		ResponseBody body=req.given().when().post().getBody();
		String msg_response=body.asString();
		String expected_msg="";
		Assert.assertEquals(expected_msg, msg_response);

	}

	// 6th scenario-verify

	@Given("a Storage Location room[uri] is defined")
	public void givenAStorageLocationRoomuriIsDefined() throws SQLException {

		logger.info("API request has been sent");	
		req.get(uri);
		logger.info("check for the number of records before posting any data");
		db_query="select count(*) from bin;";
		db_result=obj.bdconnection( db_query);
		rowcount_before=db_result;
		System.out.println("Now total number of records before passing invalid userid "+ db_result );


	}

	@When("user passes [user_id], room_id and number of [bins] to add as more than allowed")
	public void whenUserPassesuser_idRoom_idAndNumberOfbinsToAddAsMoreThanAllowed(String userid,String rows) {

		logger.info("Posting data through API");
		Map<String,String> data= new HashMap<String,String>();
		data.put("userid",userid );
		data.put("bins", rows);
		req.given().contentType("application/json").body(data).when().post(uri).then().assertThat().statusCode(201);


	}

	@Then("the system will not insert rows into the BIN table")
	public void thenTheSystemWillNotInsertRowsIntoTheBINTable() throws SQLException {

		logger.info("Check whether any rows have been inserted or not");
		db_query="select count(*) from bin;";
		db_result=obj.bdconnection(db_query);
		rowcount_before=db_result;
		System.out.println("Now total number of records before passing invalid userid "+ db_result );
		Assert.assertEquals(rowcount_before,rowcount_after);

	}

	@Then("pass an error message back saying bins to add can not be greater than 100 bins at a time.")
	public void thenPassAnErrorMessageBackSayingBinsToAddCanNotBeGreaterThan100BinsAtATime() {

		logger.info("verify the result message");
		ResponseBody body=req.given().when().post().getBody();
		String msg_response=body.asString();
		String expected_msg="";
		Assert.assertEquals(expected_msg, msg_response);

	}

	
}
