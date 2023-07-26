Feature: DATA-DRIVEN TESTING

  Background: 
    * def testData = Java.type('Runner.ADF')
    * def payload = testData.readExcelData("Sheet1")
    * print payload[rowindex]
    * def json = testData.readJsonTemplate()
    * def dataholder = testData.createJsonTemplate2(json, payload)
    * print dataholder
    * print json
    * print payload
    * print dataholder[rowindex]
    * def requestPayload = dataholder[rowindex]
    * print requestPayload

  Scenario Outline: Test with Data from Excel
    Given url 'https://jsonplaceholder.typicode.com/posts'
    And request requestPayload
    When method POST
    Then status 201
    And print response
    And match response.id == 101

    Examples: 
      | rowindex |
      |        0 |
      |        1 |
      |        2 |
