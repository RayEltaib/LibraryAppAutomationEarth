package com.cybertekschool.library.step_definitions;

import com.cybertekschool.library.pages.DashBoardPage;
import com.cybertekschool.library.utils.ui.BrowserUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class ReturnBookStepDefs extends BaseStep{

    DashBoardPage dashBoardPage = new DashBoardPage();

    @And("the user navigate to Borrowing Books")
    public void theUserNavigateToBorrowingBooks() {
        dashBoardPage.borrowingBooks.click();
    }

    @And("the user click on {string}")
    public void the_user_click_on(String string) {
        int size = dashBoardPage.returnBookBtn.size();
        dashBoardPage.returnBookBtn.get(size-1).click();
    }
    @Then("verify {string} message is displayed")
    public void verify_message_is_displayed(String string) {
        String expectedMessage = "The book has been returned..";
        BrowserUtils.verifyElementDisplayed(dashBoardPage.displayMessage);
        Assert.assertEquals("Correct message is successfully displayed",expectedMessage,dashBoardPage.displayMessage.getText());
    }

}
