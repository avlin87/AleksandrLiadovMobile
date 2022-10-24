package com.epam.mtat.scenarios;

import static com.epam.mtat.data.DataEnum.GOOGLE_LINK;
import static com.epam.mtat.data.DataEnum.GOOGLE_SEARCH_TEXT;
import static com.epam.mtat.utils.PropertiesLoader.getProperty;

import com.epam.mtat.data.DataEnum;
import com.epam.mtat.setup.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

@Slf4j
public class WebMobileTests extends BaseTest {

  @Test(groups = {"web"}, description = "Make sure that we've opened IANA homepage")
  public void simpleWebTest() {
    getDriver().get(getProperty(DataEnum.IANA_LINK)); // open IANA homepage

    // Make sure that page has been loaded completely
    getWaitDriver().until(
        wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete")
    );

    // Check IANA homepage title
    assert ((WebDriver) getDriver()).getTitle().equals(getProperty(DataEnum.IANA_TITLE)) : "This is not IANA homepage";

    // Log that test finished
    log.info("Site opening done");
  }

  @Test(groups = {"web"},
      description = "go to a Google search page and make a search using keyword ‘EPAM’. Make sure that there should be some relevant results")
  public void searchEpamInGoogle() throws NoSuchFieldException, IllegalAccessException {
    getDriver().get(getProperty(GOOGLE_LINK));

    beforeYouContinueToGooglePopUp();

    getPo().getWebElement("searchField").sendKeys(getProperty(GOOGLE_SEARCH_TEXT));
    getPo().getWebElement("searchElement").click();

    WebElement resultStartWithEpam = getPo().getWebElement("searchResult");
    getWaitDriver().until(
        ExpectedConditions.elementToBeClickable(resultStartWithEpam)
    );
    assert resultStartWithEpam.getText()
        .startsWith(getProperty(GOOGLE_SEARCH_TEXT)) : "Search result does not start with 'EPAM'";
    log.info("Search 'Epam' in google done");
  }

  private void beforeYouContinueToGooglePopUp() throws NoSuchFieldException, IllegalAccessException {
    WebElement acceptAll = getPo().getWebElement("acceptAll");

    try {
      acceptAll.isDisplayed();
      log.info("Before you continue to Google pop-up is displayed");
    } catch (NoSuchElementException e) {
      log.info("Before you continue to Google pop-up is NOT displayed");
      return;
    }

    getWaitDriver().until(ExpectedConditions.visibilityOf(acceptAll));
    getDriver().executeScript("arguments[0].scrollIntoView();", acceptAll);

    int retryCount = 0;
    while (retryCount < 3) {
      retryCount++;
      try {
        acceptAll.click();
        log.info("clicked successfully");
        break;
      } catch (Exception e) {
        log.info("Element failed to be clicked, retry count: {}", retryCount);
        acceptAll = getPo().getWebElement("acceptAll");
      }
    }
  }

}
