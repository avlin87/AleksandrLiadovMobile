package scenarios;

import static data.DataEnum.*;
import static utils.PropertiesLoader.getProperty;

import com.google.common.collect.ImmutableMap;
import data.DataEnum;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import setup.BaseTest;

public class WebMobileTests extends BaseTest {

    @Test(groups = {"web"}, description = "Make sure that we've opened IANA homepage")
    public void simpleWebTest() {
        getDriver().get(getProperty(DataEnum.IANA_LINK)); // open IANA homepage

        // Make sure that page has been loaded completely
        new WebDriverWait(getDriver(), 10).until(
                wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete")
        );

        // Check IANA homepage title
        assert ((WebDriver) getDriver()).getTitle().equals(getProperty(DataEnum.IANA_TITLE)) : "This is not IANA homepage";

        // Log that test finished
        System.out.println("Site opening done");
    }

    @Test(groups = {"web"}, description = "go to a Google search page and make a search using keyword ‘EPAM’. Make sure that there should be some relevant results")
    public void searchEpamInGoogle() throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        getDriver().get(getProperty(GOOGLE_LINK));
        getPo().getWelement("searchField").sendKeys(getProperty(GOOGLE_SEARCH_TEXT));
        getDriver().executeScript("mobile: performEditorAction", ImmutableMap.of("action", "search"));

        WebElement resultStartWithEpam = getPo().getWelement("searchResult");

        new WebDriverWait(getDriver(), 10).until(
            ExpectedConditions.elementToBeClickable(resultStartWithEpam)
        );

        assert resultStartWithEpam.getText()
            .startsWith(getProperty(GOOGLE_SEARCH_TEXT)) : "Search result does not start with 'EPAM'";

        System.out.println("Search 'Epam' in google done");
    }

}
