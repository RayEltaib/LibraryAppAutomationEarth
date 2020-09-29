package com.cybertekschool.library.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DashBoardPage extends BasePage {

    @FindBy(id = "user_count")
    public WebElement userCount;

    @FindBy(id = "book_count")
    public WebElement bookCount;

    @FindBy(id = "borrowed_books")
    public WebElement borrowedBooksCount;

    @FindBy(xpath = "//span[contains(text(),'Borrowing Books')]")
    public WebElement borrowingBooks;

    @FindBy(linkText = "Return Book")
    public List<WebElement> returnBookBtn;

    @FindBy(xpath = "//div[@class='toast-message']")
    public WebElement displayMessage;

}
