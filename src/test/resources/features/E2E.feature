@e2e

Feature: End to End Testing

  Scenario: End to End Testing
    Then new student is added using the add_user endpoint
    And user makes API call to add a book
      | name     | isbn       | year | author  | book_category_id | description               |
      | The Book | 0123456789 | 2020 | I Sahin | 1                | The book about everything |
    And user makes API call to borrow a book
    And verify response body has "The book has been borrowed" message
    And I am on the login page
    And I login to application as a student
    And the user navigate to Borrowing Books
    When the user click on "Return Book"
    Then verify "The book has been returned.." message is displayed
