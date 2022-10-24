package com.epam.mtat.setup;

import org.openqa.selenium.WebElement;

/**
 * PageObjectBase - interface that requires implementation of getWebElement method.
 */
public interface PageObjectBase {

  WebElement getWebElement(String weName)
      throws NoSuchFieldException, IllegalAccessException, InstantiationException;

}
