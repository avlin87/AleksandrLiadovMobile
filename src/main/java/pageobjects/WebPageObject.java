package pageobjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * WebPageObject - Page Object for 'web' type of application.
 */
public class WebPageObject {

  @FindBy(xpath = "//input[@name='q']")
  private WebElement searchField;

  @FindBy(xpath = "//div[@id='rso']//div[@role='link'][starts-with(text(),'EPAM')]")
  private WebElement searchResult;

  public WebPageObject(AppiumDriver appiumDriver) {
    PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);

  }
}
