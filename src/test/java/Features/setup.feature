Feature: Api

  @setup
  Scenario: api test
    * def data = [ { myNum: 1 }, { myNum: 2 }, { myNum: 3 }, { myNum: 4 }, { myNum: 5 }]

  Scenario Outline: 
    * url `https://reqres.in/api/users?${myNum}`
    * method GET

    Examples: 
      | karate.setup().data |
