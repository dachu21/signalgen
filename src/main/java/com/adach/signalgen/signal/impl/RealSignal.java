package com.adach.signalgen.signal.impl;

import com.adach.signalgen.signal.Signal;
import java.util.List;

public class RealSignal extends Signal {

  public RealSignal(String title, boolean discrete, double period, double startTime,
      double samplingFrequency,
      List<Double> realValues) {
    super("REAL", title, discrete, period, startTime, samplingFrequency, realValues);
  }
}
