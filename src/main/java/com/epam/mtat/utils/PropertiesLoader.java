package com.epam.mtat.utils;

import com.epam.mtat.data.DataEnum;
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
      readTestProperties();
      readApyKey();
    }
    String property = properties.getProperty(dataEnum.getValue());
    if (property != null) {
      return property;
    }
    throw new IllegalArgumentException("Property is empty: " + dataEnum.getValue());
  }

  private static void readTestProperties() {
    String fileLocation = "src/main/resources/data/testData.properties";
    try (FileInputStream inputStream = new FileInputStream(fileLocation)) {
      properties.load(inputStream);
    } catch (IOException e) {
      log.error("Read of properties File Failed: ", e);
    }
  }

  private static void readApyKey() {
    String fileLocation = "src/main/resources/secrets/apiKey.properties";
    try (FileInputStream inputStream = new FileInputStream(fileLocation)) {
      properties.load(inputStream);
    } catch (IOException e) {
      log.info("Read of properties Api Key Failed: ", e);
    }
  }
}
