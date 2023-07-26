Feature: GET API feature

  Background: 
    * url 'https://gorest.co.in'
    * header Accept = 'application/json'

  Scenario: get user details
    Given path '/public/v1/users'
    When method GET
    Then status 200
    And assert responseTime < 1000
    And match responseType == 'json'
    * print response
    * print responseTime
    * print responseStatus
    * print responseHeaders
    * print responseCookies
    * def jsonResponse = response
    * print jsonResponse
    * def actualname = jsonResponse.data[0].name
    * print actualname
    And match jsonResponse.data[0].name == '#present'
    And match jsonResponse.data[0].id == '#notnull'
    And match jsonResponse.data[0].gender == '#present'
    And match jsonResponse.data[0].status == '#present'
    And match jsonResponse.data[0].email == '#present'

  Scenario: get user details - user not found
    Given path '/public/v1/users'
    And path '1'
    When method GET
    Then status 404
