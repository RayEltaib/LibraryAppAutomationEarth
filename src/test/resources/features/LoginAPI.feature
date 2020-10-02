
Feature: Login through API

  Scenario Outline: Verify successful valid login attempts
    When the user send credentials as "<userType>" to login endpoint
    Then the status code reads 200
    Examples:
      | userType  |
      | librarian |
      | student   |

  Scenario Outline: Verify unsuccessful invalid login attempts
    Given the user send invalid "<userName>" and "<password>" to login endpoint
    Then the status code reads 404

    Examples:
      | userName            | password |
      |                     |          |
      | librarian13@library | 1122xx33 |
      | libarian13@library  | 87x8afWY |