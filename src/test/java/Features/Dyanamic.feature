Feature: scenario outline using a dynamic generator function

Background: 
    * url 'https://reqres.in'
    * header Accept = 'application/json'


  @setup
  Scenario:
    * def generator = function(i){ if (i == 20) return null; return { first_name: 'cat' + i, last_name: 'B' + i } }

   Scenario Outline: cat name: ${name}
    Given path '/api/users'
    And request {"first_name" : '#(first_name)', "last_name" : '#(last_name)' }
    When method post
    Then status 201
    And print response
    And match response ==  { id: '#present' ,createdAt: '#ignore' , first_name : '#(first_name)', last_name : '#(last_name)' }

    Examples:
    | karate.setup().generator |