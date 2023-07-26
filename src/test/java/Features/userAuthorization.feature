Feature: Authorisation tokens

  Background: 
    * url 'https://reqres.in/api/register'

  Scenario: Authorisation test
    And def params =
      """
      {
         "email": "eve.holt@reqres.in",
      "password": "pistol"
      }
      """
    And form fields params
    When method POST
    Then status 200
    And print response
    And def accesToken = response.token
    And print accesToken
