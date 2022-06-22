Feature: Validating place API's

  @AddPlace
  Scenario Outline: Verify if place is being Successfully added using AddPlaceAPI
    Given AddPlace Payload with "<name>" <accuracy>
    When user calls "AddPlaceAPI" with "post" http request
    # here above in When step we can re-use by just replacing AddPlaceAPI with DeleteAPI as they both are Post calls and this will be easy
    Then The API call is success with status code 200
    And "status" in response Body is "OK"
    And "scope" in response Body is "APP"
    And Verify place_Id created maps to "<name>" using "GetPlaceAPI"

    Examples:
      | name            | accuracy  |
      | Frontline house | 200       |
  #    | Backline house  | 100       |

  @DeletePlace
Scenario: Verify if Delete Place functionality is working
  Given DeletePlace Payload
  When user calls "DeletePlaceAPI" with "post" http request
  Then The API call is success with status code 200
  And "status" in response Body is "OK"