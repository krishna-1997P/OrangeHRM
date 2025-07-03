Feature: Test Employee End to End Restful Api

Background:
Given  Set Base uri

Scenario: Create new Employee Entity by using post request
Given get request Specification objcet  
And  user set "/employees" Basepath 
And  user add "Content-Type" and "application/json" header
And  user add employee postrequest  Payload
When  user select post request
Then get validatable response objcet
And  user verify response code 201
And  user verify response status "201 Created"
And use  verify response time  bellow 4000 miliseconds
And  user verify firstName should not be null
And verify email contains "@gmail" value should be present 
And verify mobile number key should be presejt
And verify "Content-Type" and "application/json" key present of not
And get employee id from response payload
Then user print response log
