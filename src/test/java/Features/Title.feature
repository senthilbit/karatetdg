Feature: Excel Data

 Background:
    * def excelclass = Java.type('Runner.ExcelUtils')
    * def payload = excelclass.readExcelData("Sheet1")
    * def rowCount = payload.length - 1
    * def repay = payload[tag]
    * print repay
    * print payload
    * print rowCount
    
 


   Scenario Outline: Data-Driven post request
    Given url 'https://jsonplaceholder.typicode.com/posts'
    And header Content-type = 'application/json; charset=UTF-8'
    And request repay
    When method Post
    Then status 201
    And print response
    And response.title = '#string'
     And response.userId = '#number'
      And response.body = '#string'
    
    
    Examples:
    | tag   |
    |  0    |
    |  1    |
    |  2    |
    |  9    |
    |  18   |
    
    
   
   