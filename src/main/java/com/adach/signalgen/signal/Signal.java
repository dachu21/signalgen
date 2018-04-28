package com.adach.signalgen.signal;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public abstract class Signal implements Serializable {

  private final String type;

  private final String title;
  private final boolean discrete;
  private final double period;

  private final double startTime;
  private final double samplingFrequency;
  private final List<Double> realValues;

  protected Signal(String type, String title, boolean discrete, double period, double startTime,
      double samplingFrequency,
      List<Double> realValues) {
    this.type = type;
    this.title = title;
    this.discrete = discrete;
    this.period = period;
    this.startTime = startTime;
    this.samplingFrequency = samplingFrequency;
    this.realValues = realValues;
  }

  public String getType() {
    return type;
  }

  public String getTitle() {
    return title;
  }

  public boolean isDiscrete() {
    return discrete;
  }

  public double getPeriod() {
    return period;
  }

  public double getStartTime() {
    return startTime;
  }

  public double getSamplingFrequency() {
    return samplingFrequency;
  }

  public List<Double> getRealValues() {
    return Collections.unmodifiableList(realValues);
  }

  @Override
  public String toString() {
    return "Signal{" +
        "type='" + type + '\'' +
        ", title='" + title + '\'' +
        ", discrete=" + discrete +
        ", period=" + period +
        ", startTime=" + startTime +
        ", samplingFrequency=" + samplingFrequency +
        ", realValues=" + realValues +
        '}';
  }
}
