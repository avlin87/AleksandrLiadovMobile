package utils;

import data.DataEnum;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;

/**
 * PropertiesLoader - util class for loading data from properties file.
 *
 * @author Aleksandr Liadov
 */
@Slf4j
public class PropertiesLoader {

  private static final Properties properties = new Properties();

  /**
   * Method returns requested value from Properties. Read value from file in case value is missing.
   *
   * @param dataEnum - type of data to be loaded
   * @return String
   */
  public static String getProperty(DataEnum dataEnum) {
    if (!properties.containsKey(dataEnum.getValue())) {
      readProperties();
    }
    return properties.getProperty(dataEnum.getValue());
  }

  private static void readProperties() {
    String fileLocation = "src/test/resources/data/testData.properties";
    try (FileInputStream inputStream = new FileInputStream(fileLocation)) {
      properties.load(inputStream);
    } catch (IOException e) {
      log.error("Read of properties File Failed: ", e);
    }
  }
}
