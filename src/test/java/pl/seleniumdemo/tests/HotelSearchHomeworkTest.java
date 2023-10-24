package pl.seleniumdemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.ResultsPage;

public class HotelSearchHomeworkTest extends BaseTest {


    @Test
    public void searchHotelWithoutNameTest() {

        ResultsPage resultsPage = new HotelSearchPage(driver)
                .setDates("25/04/2021", "30/04/2021")
                .setTravellers(0, 1)
                .performSearch();


        Assert.assertTrue(resultsPage.resultHeading.isDisplayed());
        Assert.assertEquals(resultsPage.getHeadingText(), "No Results Found");


    }

}
