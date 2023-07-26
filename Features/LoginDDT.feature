Feature: Login valid credentials
 
  @Regression
  Scenario Outline: Successful login with valid credentials
    Given User launch browser
    And Redirect to "http://localhost/opencart/upload/"
    When Navigate to My Account
    And Click Login
    And User enter email as "<username>" and password as "<password>"
    And Click Login button
    Then user navigate to My Account Page

    Examples: 
      | username                | password               | 
      | first1last1@yopmail.com |    first1last1@yopmail$ | 
      | first1last1@yopmail.com |     first1last1@yopmail | 
