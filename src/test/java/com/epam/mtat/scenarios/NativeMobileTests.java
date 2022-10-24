package com.epam.mtat.scenarios;

import static com.epam.mtat.utils.PropertiesLoader.getProperty;

import com.epam.mtat.data.DataEnum;
import com.epam.mtat.setup.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

@Slf4j
public class NativeMobileTests extends BaseTest {

  @Test(groups = {"native"},
      priority = 1,
      description = "This simple test just click on the Sign In button")
  public void simpleNativeTest() throws IllegalAccessException, NoSuchFieldException {
    getPo().getWebElement("signInBtn").click();
    log.info("Simplest Android native test done");
  }

  @Test(groups = {"native"},
      priority = 2,
      description = "register a new account and then sign in. Make sure that you are on the BudgetActivity page.")
  public void registerAccountNativeTest() throws NoSuchFieldException, IllegalAccessException {
    getPo().getWebElement("newAccountBtn").click();
    getPo().getWebElement("regEmailField").sendKeys(getProperty(DataEnum.USER_EMAIL));
    getPo().getWebElement("regUserNameField").sendKeys(getProperty(DataEnum.USER_NAME));
    getPo().getWebElement("regPassword").sendKeys(getProperty(DataEnum.USER_PASSWORD));
    getPo().getWebElement("regConfPassword").sendKeys(getProperty(DataEnum.USER_PASSWORD));
    getPo().getWebElement("regAccountBtn").click();

    getPo().getWebElement("loginEmailField").sendKeys(getProperty(DataEnum.USER_EMAIL));
    getPo().getWebElement("loginPassword").sendKeys(getProperty(DataEnum.USER_PASSWORD));
    getPo().getWebElement("signInBtn").click();

    assert getPo().getWebElement("actionBarText").getText()
        .startsWith(getProperty(DataEnum.TARGET_PAGE)) : "This is not BudgetActivity page";

    log.info("register a new account native test done");
  }
}
