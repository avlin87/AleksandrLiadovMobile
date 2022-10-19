package setup;

import io.appium.java_client.AppiumDriver;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

/**
 * BaseTest - base class for test execution.
 */
public class BaseTest implements Driver {

  private static AppiumDriver appiumDriver; // singleton
  private static PageObject po;
  private static WebDriverWait driverWait;

  @Override
  public AppiumDriver getDriver() {
    return appiumDriver;
  }

  @Override
  public WebDriverWait getWaitDriver() {
    return driverWait;
  }

  public PageObject getPo() {
    return po;
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
  @Parameters({"platformName", "appType", "deviceName", "browserName", "app"})
  @BeforeSuite(alwaysRun = true)
  public void setUp(String platformName, String appType, String deviceName,
      @Optional("") String browserName, @Optional("") String app) throws Exception {
    System.out.println("Before: app type - " + appType);
    setAppiumDriver(platformName, deviceName, browserName, app);
    setPageObject(appType, appiumDriver);
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() {
    System.out.println("After");
    appiumDriver.closeApp();
  }

  private void setAppiumDriver(String platformName, String deviceName,
      String browserName, String app) {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    //mandatory Android capabilities
    capabilities.setCapability("platformName", platformName);
    capabilities.setCapability("deviceName", deviceName);

    if (app.endsWith(".apk")) {
      capabilities.setCapability("app", (new File(app)).getAbsolutePath());
    }

    capabilities.setCapability("browserName", browserName);
    capabilities.setCapability("chromedriverDisableBuildCheck", "true");

    try {
      appiumDriver = new AppiumDriver(new URL(System.getProperty("ts.appium")), capabilities);
      driverWait = new WebDriverWait(appiumDriver, 10);
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }

    // Timeouts tuning
    appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

  }

  private void setPageObject(String appType, AppiumDriver appiumDriver) throws Exception {
    po = new pageobjects.PageObject(appType, appiumDriver);
  }

}
