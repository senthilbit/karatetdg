Feature: Test demo API

Background: 
* url 'https://restful-booker.herokuapp.com'

  Scenario: Run a sample Get API
    * def booking =
    """
    {
"username": "admin",
"password": "password123"
}
    """
    
  
    * path '/auth'
    * header Accept = 'application/json'
    * request booking
    * method POST
    * status 200
    * print response
    * def token = response.token
    
    #Create booking
    
    * def bookingi =
    """
    {
    "firstname": "Jim",
    "lastname": "Brown",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
    "checkin": "2023-06-01",
    "checkout": "2023-06-15"
    },
    "additionalneeds": "Breakfast"
    }
    
    """
    
    * def head = {'Content-Type': 'application/json','Accept': 'application/json','Cookie': `token=${token}` }
    
    * print token
  
    * path '/booking'
    * headers head
    * request bookingi 
    * method POST
    * status 200
    * print response
    * match response.booking.firstname == 'Jim'
    * match response.booking.lastname == 'Brown'
    * def bookingid = response.bookingid
    
   
    # Find booking by name
    * path '/booking'
    * param firstname = bookingi.firstname
    * param lastname = bookingi.lastname
    * method GET
    * status 200
    * print response
    * match response contains { bookingid: '#(bookingid)' }
    
    # Find booking by Date
    * path '/booking'
    * param cheakin = bookingi.cheakin
    * param checkout = bookingi.checkout
    * method GET
    * status 200
    * print response
    * match response contains { bookingid: '#(bookingid)' }