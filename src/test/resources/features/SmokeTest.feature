@ELE-97
Feature: API Smoke Test

	@ELE-95 @ELE-93 @ELE-33 @ELE-98
	Scenario Outline: API Login
		When the user send credentials as "<userType>" to login endpoint
		Then the status code reads 200
		Examples:
		  | userType  |
		  | librarian |
		  | student   |
	
	@ELE-96 @ELE-93 @ELE-33 @ELE-98
	Scenario: API Borrow Book
		Given user makes API call to borrow a book
		Then verify response body has "The book has been borrowed" message
