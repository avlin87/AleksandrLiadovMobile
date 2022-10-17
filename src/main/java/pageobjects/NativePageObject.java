package pageobjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

/**
 * NativePageObject - Page Object for 'native' type of application.
 */
public class NativePageObject {

  @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/email_sign_in_button")
  WebElement signInBtn;

  @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/login_email")
  WebElement loginEmailField;

  @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/login_pwd")
  WebElement loginPassword;

  @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout"
      + "/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]"
      + "/android.view.ViewGroup/android.widget.TextView")
  WebElement actionBarText;

  @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/register_button")
  WebElement newAccountBtn;

  @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_email")
  WebElement regEmailField;

  @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_username")
  WebElement regUserNameField;

  @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_password")
  WebElement regPassword;

  @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_confirm_password")
  WebElement regConfPassword;

  @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/register_new_account_button")
  WebElement regAccountBtn;

  public NativePageObject(AppiumDriver appiumDriver) {
    PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
  }


}
