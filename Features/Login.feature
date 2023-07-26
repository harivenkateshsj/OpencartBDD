Feature: Login valid credentials
  
  @Sanity
  Scenario: Successful login with valid credentials
    Given User launch browser
    And Redirect to "http://localhost/opencart/upload/"
    When Navigate to My Account
    And Click Login
    And User enter email as "first1last1@yopmail.com" and password as "first1last1@yopmail"
    And Click Login button
    Then user navigate to My Account Page
