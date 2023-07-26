Feature: Create an airline



  Scenario: Create an airline with created json payload
    Given url "https://api.instantwebtools.net/v1/airlines"
    * def requestPayload = {}
    * requestPayload.id = 423432
    * requestPayload.name = "Sri Lankan Airways", requestPayload.country = "Sri Lanka"
    * requestPayload.logo = "https://upload.wikimedia.org/wikipedia/en/thumb/9/9b/Qatar_Airways_Logo.svg/sri_lanka.png"
    * requestPayload.slogan = "From Sri Lanka"
    * requestPayload.head_quaters = "Katunayake, Sri Lanka"
    * requestPayload.website = "www.srilankaairways.com"
    * requestPayload.established = 1990
    * requestPayload.address = []
    * requestPayload.address[0] = {}
    * requestPayload.address[0].city = "BLR"
    * requestPayload.address[0].state = "KA"
    * requestPayload.address[1] = {}
    * requestPayload.address[1].city = "DL"
    * requestPayload.address[1].state = "DL"
    * print requestPayload

  Scenario: Create an airline with json payload from external source
    Given url "https://api.instantwebtools.net/v1/airlines"
    * def requestPayload = read('classpath:Data/Creteairpayload.json')
    * set requestPayload.id = 324324
    * print requestPayload
    * remove requestPayload.id



  Scenario: Create an airline with json payload from external source
    Given url "https://api.instantwebtools.net/v1/airlines"
    * def requestPayload = read('classpath:Data/Creteairpayload.json')
    * set requestPayload.ceo = "Amod Mahajan"
    * set requestPayload.foo =
    """
    {
    "foo1" : {
      "foo2": "boo1"
    }
  }
    """
    * set requestPayload.foo.foo1.foo2 = "boo2"
    * set requestPayload.Skills[0] =
    """
    {
      "sub": "Java"
    }
    """
    * set requestPayload.Skills[1] =
    """
    {
      "sub": "Selenium"
    }
    """
    * print requestPayload