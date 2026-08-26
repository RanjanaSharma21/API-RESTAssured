Feature: API test cases

  Background:
    Given a token is created

  @apiCrudIntegration
  Scenario: Verify complete Employee lifecycle via API
  #1 CREATE LIFECYCLE
    Given a request is prepared to create an employee using API
    When a POST call is made to create an employee
    Then the status code for this request is 201
    And the request body must match the response body
    And the employee is stored as global variable "data.empNumber"

  # 2. GET LIFECYCLE
    Given a request is prepared to get an employee
    When a GET call is made to get a created employee
    Then the status code for this request is 200
    And the employee number "data.empNumber" must match the global variable employee number

  # 3. UPDATE LIFECYCLE
    Given a request is prepared to update an employee
    When a PUT call is made to update an employee
    Then the status code for this request is 200
    And the employee number "data.empNumber" must match the global variable employee number

  # 4. DELETE LIFECYCLE
    Given a request is prepared to delete an employee
    When a DELETE call is made to delete the employee
    Then the status code for this request is 200
    And the deleted employee numbers must match the requested employee numbers

  @map
  Scenario: Create an employee using Map request body
    Given a request is prepared to create an employee using Map format
    When a POST call is made to create an employee
    Then the status code for this request is 201
    And the Map request body must match the response body
    And the employee is stored as global variable "data.empNumber"

  @rawjson
  Scenario: Create an employee using JSON request body
    Given a request is prepared to create an employee using JSON format
    When a POST call is made to create an employee
    Then the status code for this request is 201
    And the JSON request body must match the response body
    And the employee is stored as global variable "data.empNumber"

  @dynamicjson
  Scenario Outline: Create an employee using dynamic JSON request body
    Given a request is prepared to create an employee using dynamic JSON format with "<firstName>", "<lastName>", "<middleName>", "<gender>", "<birthday>", "<jobTitle>"
    When a POST call is made to create an employee
    Then the status code for this request is 201
    And the dynamic JSON request body must match the response body
    And the employee is stored as global variable "data.empNumber"

    Examples:
      | firstName | lastName | middleName | gender | birthday   | jobTitle |
      | Janki     | Vallabh  | Sita       | F      | 1990-01-15 | SDET     |
      | Ranjana   | Sharma   | ms         | F      | 2004-11-09 | SDET     |
      | Palash    | Sharma   |            | M      | 2006-05-15 | SDET     |

  @lombokpojo
  Scenario Outline: Create an employee using lombok pojo dynamic JSON request body
    Given a request is prepared to create an employee using lombok pojo dynamic JSON format with "<firstName>", "<lastName>", "<middleName>", "<gender>", "<birthday>", "<jobTitle>"
    When a POST call is made to create an employee
    Then the status code for this request is 201
    And the POJO request body must match the response body
    And the response structure must match the employee JSON schema blueprint
    And the employee is stored as global variable "data.empNumber"

    Examples:
      | firstName | lastName | middleName | gender | birthday   | jobTitle |
      | Janki     | Vallabh  | Sita       | F      | 1990-01-15 | SDET     |
      | Ranjana   | Sharma   | ms         | F      | 2004-11-09 | SDET     |
      | Palash    | Sharma   |            | M      | 2006-05-15 | SDET     |