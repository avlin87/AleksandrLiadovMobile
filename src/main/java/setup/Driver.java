package setup;

import io.appium.java_client.MobileDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Driver - interface that requires implementation of getDriver method.
 */
public interface Driver {

  MobileDriver getDriver();

  WebDriverWait getWaitDriver();

}
