Feature: Login valid credentials
 
  @Sanity
  Scenario Outline: Successful login with valid credentials
    Given User launch browser
    And Redirect to "http://localhost/opencart/upload/"
    When Navigate to My Account
    And Click Login
    Then Check User navigates to MyAccount Page by passing Email and Password with excel row "<rows>"

    Examples: 
      | rows | 
      | 1 |  
      | 2 |  
      | 3 |
      | 4 |
      | 5 |
      
