Feature: Emmeded Expression

  Scenario: Expression
    * def employee = { fName: 'ramesh', lName: 'deshamukh', mName: 'sagar' }
    * print employee
    * def fName = 'rahul'
    * def lName = 'deshamukh'
    * def mName = 'sagar'
    * def employee1 = { fName: '#(fName)', lName: '#(lName)', mName: '#(mName)' }
    * print employee1
    * def employee2 = { fullName: '#(fName + " " + lName)', lName: '#(lName)', mName: '#(mName)' }
    * print employee2
    * def employee3 = { fullName: '#(employee.fName)', lName: '#(lName)', mName: '##(mName)' }
    * print employee3