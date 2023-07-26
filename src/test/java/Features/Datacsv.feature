Feature: Data-driven testing wit csv





  Scenario Outline: Data-Driven post request
    Given url <posts>
    And header Content-type = <Content-type>
    And request
      """
      {
      title: <title>
      body: <body>
      userId: <userId>
      }
      """
    When method <method>
    Then status <status>
    And print response
    And match response contains
      """
      {
      title: <title>
      body: <body>
      userId: <userId>
      }
      """

    Examples:
      | read('classpath:Data/postcsv.csv')  |