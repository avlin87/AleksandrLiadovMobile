package scenarios;

import static utils.PropertiesLoader.getProperty;

import data.DataEnum;
import org.testng.annotations.Test;
import setup.BaseTest;

public class NativeMobileTests extends BaseTest {

  @Test(groups = {"native"},
      priority = 1,
      description = "This simple test just click on the Sign In button")
  public void simpleNativeTest() throws IllegalAccessException, NoSuchFieldException, InstantiationException {
    getPo().getWelement("signInBtn").click();
    System.out.println("Simplest Android native test done");
  }

  @Test(groups = {"native"},
      priority = 2,
      description = "register a new account and then sign in. Make sure that you are on the BudgetActivity page.")
  public void registerAccountNativeTest() throws NoSuchFieldException, IllegalAccessException, InstantiationException {
    getPo().getWelement("newAccountBtn").click();
    getPo().getWelement("regEmailField").sendKeys(getProperty(DataEnum.USER_EMAIL));
    getPo().getWelement("regUserNameField").sendKeys(getProperty(DataEnum.USER_NAME));
    getPo().getWelement("regPassword").sendKeys(getProperty(DataEnum.USER_PASSWORD));
    getPo().getWelement("regConfPassword").sendKeys(getProperty(DataEnum.USER_PASSWORD));
    getPo().getWelement("regAccountBtn").click();

    getPo().getWelement("loginEmailField").sendKeys(getProperty(DataEnum.USER_EMAIL));
    getPo().getWelement("loginPassword").sendKeys(getProperty(DataEnum.USER_PASSWORD));
    getPo().getWelement("signInBtn").click();

    assert getPo().getWelement("actionBarText").getText()
        .equals(getProperty(DataEnum.TARGET_PAGE)) : "This is not BudgetActivity page";

    System.out.println("register a new account native test done");
  }
}
