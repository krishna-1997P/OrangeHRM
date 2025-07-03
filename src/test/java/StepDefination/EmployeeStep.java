package StepDefination;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.hamcrest.Matchers;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class EmployeeStep
{
	RequestSpecification requestSpec ;
	Response response;
	
	ValidatableResponse validateResponse ;
	
	
	
	
	@Given("Set Base uri")
	public void set_base_uri() {
	  
		RestAssured.baseURI="http://localhost:3000";
	}

	
	@Given("get request Specification objcet")
	public void get_request_specification_objcet() 
	{
		
		requestSpec = 	RestAssured.given();
	}

	@Given("user set {string} Basepath")
	public void user_set_basepath(String BasePath) 
	{
	
		requestSpec.basePath(BasePath);
		//RestAssured.basePath=BasePath;    
	}

	@Given("user add {string} and {string} header")
	public void user_add_and_header(String header, String key) {
	   
		
		
		requestSpec.header(header, key);	
	}

	@Given("user add employee postrequest  Payload")
	public void user_add_employee_postrequest_payload() throws Exception 
	{
		//String path = System.getProperty("user.dir")+"//src//test//resources//RequestPayload//PostRequestPayload.json";
 
		String path = System.getProperty("user.dir")+"//src//test//resources//RequestPayload//PostRequestPayload.json";
		
		//String requestPayload = new String (Files.readAllBytes(Paths.get(path)));
		byte [] jsonArray = Files.readAllBytes(Paths.get(path));
		
		//Convert array data into string 
		
		String requesstPayload = new String (jsonArray);
		requestSpec.body(requesstPayload);
	   
	}

	@When("user select post request")
	public void user_select_post_request() 
	{
		
		 response = requestSpec.post();
	    
	}

	@Then("get validatable response objcet")
	public void get_validatable_response_objcet() 
	{
		
	 validateResponse = response.then().assertThat();
	  
	
	}

	@Then("user verify response code {int}")
	public void user_verify_response_code(Integer statusCode)
	{
		
		validateResponse.statusCode(Matchers.equalTo(statusCode));
	}

	@Then("user verify response status {string}")
	public void user_verify_response_status(String StatusLine) 
	{
		validateResponse.statusLine(Matchers.containsString(StatusLine));
	 
	}

	@Then("use  verify response time  bellow {int} miliseconds")
	public void use_verify_response_time_bellow_miliseconds(Integer responseTime)
	{
		validateResponse.time(Matchers.lessThan((long)responseTime));
	}
	
	

	@Then("user verify firstName should not be null")
	public void user_verify_first_name_should_not_be_null() 
	{
		
		//validateResponse.body("firstName", Matchers.notNullValue());

	}

	@Then("verify email contains {string} value should be present")
	public void verify_email_contains_value_should_be_present(String gmail) 
	{
		
		//validateResponse.body("emaill", Matchers.contains(gmail));
	}

	@Then("verify mobile number key should be presejt")
	public void verify_mobile_number_key_should_be_presejt() 
	{
		validateResponse.body("", Matchers.hasKey("mobileNo"));


	}

	@Then("verify {string} and {string} key present of not")
	public void verify_and_key_present_of_not(String Kay, String Value)
	{
		
		validateResponse.header(Kay, Value);

}

	@Then("get employee id from response payload")
	public void get_employee_id_from_response_payload() 
	{
	
	    ResponseBody respBody = response.getBody();
	   String EmpId = respBody.jsonPath().getString("id");
	    System.out.println(EmpId);
	    
	}

	@Then("user print response log")
	public void user_print_response_log() 
	{
		
		validateResponse.log().all();
	   }


}
