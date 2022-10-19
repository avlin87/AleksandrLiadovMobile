package setup;

import org.openqa.selenium.WebElement;

/**
 * PageObject - interface that requires implementation of getWelement method.
 */
public interface PageObject {

  WebElement getWelement(String weName)
      throws NoSuchFieldException, IllegalAccessException, InstantiationException;

}
