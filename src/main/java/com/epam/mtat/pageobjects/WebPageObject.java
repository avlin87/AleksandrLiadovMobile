package com.epam.mtat.pageobjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * WebPageObject - Page Object for 'web' type of application.
 */
public class WebPageObject {

  @FindBy(xpath = "//button[@id='L2AGLb']/div[starts-with(text(),'Accept all')]")
  private WebElement acceptAll;

  @FindBy(xpath = "//input[@name='q']")
  private WebElement searchField;

  @FindBy(xpath = "//div[@class='s49v2']//span[contains(translate(.,'aemp','AEMP'),'EPAM')]")
  private WebElement searchElement;

  @FindBy(xpath = "//div[@id='rso']//div[@role='link'][starts-with(text(),'EPAM')]")
  private WebElement searchResult;

  public WebPageObject(AppiumDriver<WebElement> appiumDriver) {
    PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
  }
}
