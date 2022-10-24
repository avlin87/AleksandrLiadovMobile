package com.epam.mtat.pageobjects;

import com.epam.mtat.setup.PageObjectBase;
import io.appium.java_client.AppiumDriver;
import java.lang.reflect.Field;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;

/**
 * PageObject - performs creation of requested Page Object.
 */
@Slf4j
public class PageObject implements PageObjectBase {

  private final Object somePageObject; // it should be set of web page or EPAM Test App WebElements

  /**
   * Constructor - that create page object based on appType.
   *
   * @param appType      - type of app such as 'web' for browser app.
   * @param appiumDriver - driver.
   * @throws Exception - thrown in case provided appType is unknown.
   */
  public PageObject(String appType, AppiumDriver<WebElement> appiumDriver) throws Exception {
    log.info("Current app type: " + appType);
    switch (appType) {
      case "web":
        somePageObject = new WebPageObject(appiumDriver);
        break;
      case "native":
        somePageObject = new NativePageObject(appiumDriver);
        break;
      default:
        throw new Exception("Can't create a page object for " + appType);
    }
  }

  @Override
  public WebElement getWebElement(String weName)
      throws NoSuchFieldException, IllegalAccessException {
    // use reflection technique
    Field field = somePageObject.getClass().getDeclaredField(weName);
    field.setAccessible(true);
    return (WebElement) field.get(somePageObject);
  }
}