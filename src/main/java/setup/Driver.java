package setup;

import io.appium.java_client.MobileDriver;

/**
 * Driver - interface that requires implementation of getDriver method.
 */
public interface Driver {

  MobileDriver getDriver();

}
