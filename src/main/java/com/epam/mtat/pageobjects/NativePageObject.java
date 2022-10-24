package com.epam.mtat.pageobjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

/**
 * NativePageObject - Page Object for 'native' type of application.
 */
public class NativePageObject {

  @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/email_sign_in_button")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeCell/XCUIElementTypeStaticText[@value='Sign In']")
  private WebElement signInBtn;

  @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/login_email")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeCell/XCUIElementTypeTextField")
  private WebElement loginEmailField;

  @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/login_pwd")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeCell/XCUIElementTypeSecureTextField")
  private WebElement loginPassword;

  @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout"
      + "/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]"
      + "/android.view.ViewGroup/android.widget.TextView")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar//XCUIElementTypeOther[@name='Budget']")
  private WebElement actionBarText;

  @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/register_button")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeCell"
      + "/XCUIElementTypeStaticText[@value='Register new account']")
  private WebElement newAccountBtn;

  @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_email")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@value='Email']"
      + "//parent::XCUIElementTypeCell//XCUIElementTypeTextField")
  private WebElement regEmailField;

  @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_username")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@value='Username']"
      + "//parent::XCUIElementTypeCell//XCUIElementTypeTextField")
  private WebElement regUserNameField;

  @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_password")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@value='Password']"
      + "//parent::XCUIElementTypeCell//XCUIElementTypeSecureTextField")
  private WebElement regPassword;

  @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_confirm_password")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@value='Confirm password']"
      + "//parent::XCUIElementTypeCell//XCUIElementTypeSecureTextField")
  private WebElement regConfPassword;

  @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/register_new_account_button")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@value='Register new account']")
  private WebElement regAccountBtn;

  public NativePageObject(AppiumDriver<WebElement> appiumDriver) {
    PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
  }
}
