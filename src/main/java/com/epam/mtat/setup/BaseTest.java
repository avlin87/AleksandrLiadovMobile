package com.epam.mtat.setup;

import static com.epam.mtat.data.DataEnum.API_KEY;
import static com.epam.mtat.utils.PropertiesLoader.getProperty;
import static java.lang.String.format;

import com.epam.mtat.pageobjects.PageObject;
import io.appium.java_client.AppiumDriver;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

/**
 * BaseTest - base class for test execution.
 */
@Slf4j
public class BaseTest implements DriverProvider {

  private static AppiumDriver<WebElement> appiumDriver; // singleton
  private static PageObject pageObject;
  private static WebDriverWait driverWait;

  @Override
  public AppiumDriver<WebElement> getDriver() {
    return appiumDriver;
  }

  @Override
  public WebDriverWait getWaitDriver() {
    return driverWait;
  }

  public PageObject getPo() {
    return pageObject;
  }

  /**
   * Method prepares test environment.
   *
   * @param platformName - type of operating system.
   * @param appType      - type of app such as 'web' for browser app.
   * @param deviceName   - name of device under test.
   * @param browserName  - name of browser under test.
   * @param app          - name of application under test.
   * @throws Exception - thrown in case Page Object not created.
   */
  @Parameters({"platformName", "appType", "deviceName", "browserName", "app", "isCloud"})
  @BeforeSuite(alwaysRun = true)
  public void setUp(String platformName, String appType, String deviceName,
      @Optional("") String browserName, @Optional("") String app,
      @Optional("false") Boolean isCloud) throws Exception {
    log.info("Before: app type - " + appType);
    log.info("Before: app isCloud - " + isCloud);
    setAppiumDriver(platformName, deviceName, browserName, app, isCloud);
    setPageObject(appType, appiumDriver);
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() {
    log.info("After");
    appiumDriver.closeApp();
  }

  private void setAppiumDriver(String platformName, String deviceName,
      String browserName, String app, Boolean isCloud) {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    //mandatory Android capabilities
    capabilities.setCapability("platformName", platformName);
    capabilities.setCapability("deviceName", deviceName);

    if (app.endsWith(".apk") || app.endsWith(".ipa")) {
      capabilities.setCapability("app", (new File(app)).getAbsolutePath());
    }

    capabilities.setCapability("browserName", browserName);
    capabilities.setCapability("chromedriverDisableBuildCheck", "true");

    try {
      if (isCloud) {
        String key = URLEncoder.encode(getProperty(API_KEY), StandardCharsets.UTF_8.name());
        appiumDriver = new AppiumDriver<>(
            new URL(format("https://%s:%s@%s/wd/hub",
                System.getProperty("ts.appiumCloudProject"),
                key,
                System.getProperty("ts.appiumCloudHub"))),
            capabilities);
      } else {
        appiumDriver = new AppiumDriver<>(new URL(System.getProperty("ts.appium")), capabilities);
      }

      driverWait = new WebDriverWait(appiumDriver, 10);
      log.info("Driver initiated successfully");
    } catch (MalformedURLException e) {
      log.error("Driver initialization failed: ", e);
    } catch (UnsupportedEncodingException e) {
      log.error("Encode of API KEY failed: ", e);
    }

    // Timeouts tuning
    appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }

  private void setPageObject(String appType, AppiumDriver<WebElement> appiumDriver)
      throws Exception {
    pageObject = new PageObject(appType, appiumDriver);
  }

}
