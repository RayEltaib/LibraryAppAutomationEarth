@e2e

Feature: End to End Testing

  Scenario: End to End Testing
    Then new librarian is added using the add_user endpoint
    Then user makes API call to add a book
      | name          | isbn       | year | author               | book_category_id | description                                                            |
      | Infinite Jest | 0316066524 | 1996 | David Foster Wallace | 1                | Infinite Jest is a 1996 novel by American writer David Foster Wallace. |
    Then user makes API call to borrow a book
    And verify response body has "The book has been borrowed" message
    Then I am on the login page
    And I login to application as a student
    Then the user navigate to Borrowing Books
    Then the user click on "Return Book"
    And verify "The book has been returned.." message is displayed
