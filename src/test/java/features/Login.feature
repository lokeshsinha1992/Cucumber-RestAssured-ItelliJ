Feature:  Application Login

 Scenario: Home page default login
   Given User is on banking landing page
   When User login into application with username "Jenny" and password "doey"
   Then Home page is populated
   And Cards are displayed "true"



  Scenario: Home page default login
    Given User is on banking landing page
    When User login into application with username "John" and password "doe"
    Then Home page is populated
    And Cards are displayed "false"