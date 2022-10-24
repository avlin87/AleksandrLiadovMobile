package com.epam.mtat.setup;

import io.appium.java_client.MobileDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * DriverProvider - interface that requires implementation of getDriver method.
 */
public interface DriverProvider {

  MobileDriver<?> getDriver();

  WebDriverWait getWaitDriver();

}
