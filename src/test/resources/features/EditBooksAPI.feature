@mrt
Feature: Librarian should be able to edit a book

  Scenario: Librarian is able to edit a book
    Given the user logged in as "librarian" in API
    When the user edit the book using following info
      | name             | Interesting Name  |
      | isbn             | 121212121212      |
      | year             | 1900              |
      | author           | Anonymous         |
      | book_category_id | 2                 |
      | description      | EditBookAPI check |
      | id               | 750               |

    Then the correct message should be received
#    And the book info should be changed to as the following
#      | name             | Interesting Name  |
#      | isbn             | 121212121212      |
#      | year             | 1900              |
#      | author           | Anonymous         |
#      | book_category_id | 2                 |
#      | description      | EditBookAPI check |
#      | id               | 750               |