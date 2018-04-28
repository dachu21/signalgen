package com.adach.signalgen.generator;

import com.adach.signalgen.parameters.SignalParameters;
import com.adach.signalgen.signal.impl.RealSignal;
import java.util.ArrayList;
import java.util.List;

public abstract class SignalGenerator {

  protected boolean discrete = false;
  protected double period = 0;

  public RealSignal generateRealSignal(SignalParameters params, String title) {
    double startTime = params.get("t1");
    double endTime = params.get("t1") + params.get("d");
    double samplingFrequency = params.get("f");

    List<Double> realValues = new ArrayList<>();
    int sampleNumber = 0;
    for (double t = startTime; t <= endTime; t += 1 / samplingFrequency) {
      sampleNumber++;
      realValues.add(calculateRealValue(params, t, sampleNumber));
    }

    return new RealSignal(title, discrete, period, startTime, samplingFrequency, realValues);
  }

  protected abstract Double calculateRealValue(SignalParameters params, double t, int sampleNumber);
}
