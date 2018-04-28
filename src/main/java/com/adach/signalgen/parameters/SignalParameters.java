package com.adach.signalgen.parameters;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class SignalParameters {

  private final Map<String, Double> parameters;

  public SignalParameters(Map<String, Double> parameters) {
    this.parameters = parameters;
  }

  public SignalParameters(Properties properties) {
    parameters = new HashMap<>();
    for (String key : properties.stringPropertyNames()) {
      parameters.put(key, Double.parseDouble(properties.getProperty(key)));
    }
  }

  public Double get(String parameterName) {
    return parameters.get(parameterName);
  }
}
