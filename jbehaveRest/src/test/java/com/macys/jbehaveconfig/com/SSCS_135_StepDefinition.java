package com.macys.jbehaveconfig.com;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jbehave.core.annotations.*;
import org.junit.Assert;

import com.databaseconnection.mcys.com.DatabaseConnection;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class SSCS_135_StepDefinition {

	//1-

	DatabaseConnection obj=new DatabaseConnection();
	RequestSpecification req = RestAssured.given();

	int db_result=0;
	String db_query;
	String uri;
	int status_code=0;
	int rowcount_before=0;
	int rowcount_after=0;
	static Logger logger= Logger.getLogger(SSCS_135_StepDefinition.class);
	Response response;
	JsonPath jsonread;
	//Scenario: Verifying service when user passes div number and store number with room and bins defined
	@Given("a Room is defined and the Room has bins Defined")
	public void givenARoomIsDefinedAndTheRoomHasBinsDefined() throws SQLException {

		db_query="select count(*) from Bin where roomid='';";
		db_result=obj.bdconnection( db_query);
		Assert.assertTrue(db_result>=0);

		System.out.println("Room and bins are defined");


	}

	@When("the User passes division[div_num] and store[store]")
	public void whenTheUserPassesDivisiondiv_numAndStorestore(int div_num,int store_num) {

		response=req.get(" http://localhost:8080/binning/storeFulfillment/div/"+div_num+"/store/"+store_num+"");

	}

	@Then("the service will return array of rooms defined with the number of bins")

	public void thenTheServiceWillReturnArrayOfRoomsDefinedWithTheNumberOfBins() {

		String resp=req.toString();
		jsonread=new JsonPath(resp);
		String bin=jsonread.getString("noOfBins");
		System.out.println("Number of bins" + bin);
		//  req.given().when().post().then().assertThat().body("contains, arg1)
		int count=bin.length();
		System.out.println("Number of rooms"+ count);
		Assert.assertTrue(count>0);


	}


	//2-

	@Given("Room is defined and the Room has bins Defined")

	public void givenRoomIsDefinedAndTheRoomHasBinsDefined() throws SQLException {
		db_query="select count(*) from Bin where roomid='';";
		db_result=obj.bdconnection(db_query);
		Assert.assertTrue(db_result>=0);
		System.out.println("Room and bins are defined");
	}

	@When("the User passes room_id[room_id]")
	
	public void whenTheUserPassesRoom_idroom_id() {
		// PENDING
	}

	@Then("the service will return the array of bins defined for the room")
	public void thenTheServiceWillReturnTheArrayOfBinsDefinedForTheRoom() {
		// PENDING
	}


	//	Scenario: Verifying service when user passes only room id when room is defined without  bins.
	@Given("a Room is defined and the Room has no bins defined")

	public void givenARoomIsDefinedAndTheRoomHasNoBinsDefined() throws SQLException {

		db_query="select count(*) from Bin where roomid='';";
		db_result=obj.bdconnection( db_query);
		Assert.assertTrue(db_result==0);

	}

	@When("the User passes room_id [room_id]")

	public void whenTheUserPassesRoom_idroom_id1() {

		response=req.get(" http://localhost:8080/D01212121");

	}

	@Then("the service will return valid return code with room information but an empty bin array")
	public void thenTheServiceWillReturnValidReturnCodeWithRoomInformationButAnEmptyBinArray() {

		response.then().assertThat().statusCode(200);
		String resp=req.toString();
		jsonread=new JsonPath(resp);
		String bin=jsonread.getString("noOfBins");
		int bin_num=Integer.parseInt(bin);
		System.out.println("Number of bins" + bin);
		Assert.assertTrue(bin_num==0);



	}

	//4-Scenario: Verifying service when user passes roomid along with start position and number of records when room is defined with many bins
	@Given("a Room is defined and the room has many bins defined")
	public void givenARoomIsDefinedAndTheRoomHasManyBinsDefined() {




	}

	@When("the User passes  room_id [room_id]  along with start position 0 [start_position], number of records 5[num_of_records]")
	public void whenTheUserPassesRoom_idroom_idAlongWithStartPosition0start_positionNumberOfRecords5num_of_records() {



	}

	@Then("service will return bin information for bin 1 through 5")
	public void thenServiceWillReturnBinInformationForBin1Through5() {


	}


	//5-Scenario: Verifying services when user passes division and store number with room undefined.

	@Given("a Room is not defined")
	public void givenARoomIsNotDefined() {



	}

	@When("User passes  division[div_num], store[store_num]")
	public void whenUserPassesDivisiondiv_numStorestore_num() {



	}

	@Then("service will return good return code with  empty room array.")
	public void thenServiceWillReturnGoodReturnCodeWithEmptyRoomArray() {



	}




}
