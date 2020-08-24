package io.github.mkdev.other;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadProperties {
  private Properties prop;

  /** Method load properties from file.
   * @return new Properties();
   */
  public Properties getProp() {
    try (InputStream input = LoadProperties.class.getClassLoader()
        .getResourceAsStream("application.properties")) {
      prop = new Properties();
      if (input == null) {
        System.out.println("Sorry, unable to find config.properties");
      }
      prop.load(input);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return prop;
  }

}
