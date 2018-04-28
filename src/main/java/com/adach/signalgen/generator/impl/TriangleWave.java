package com.adach.signalgen.generator.impl;

import com.adach.signalgen.generator.SignalGenerator;
import com.adach.signalgen.parameters.SignalParameters;

public class TriangleWave extends SignalGenerator {

  public TriangleWave() {
    this.discrete = false;
  }

  @Override
  protected Double calculateRealValue(SignalParameters params, double t, int sampleNumber) {
    this.period = params.get("T");

    double T = params.get("T");
    double kw = params.get("kw");
    double A = params.get("A");
    int k = (int) (t / T);
    if (t >= k * T && t < kw * T + k * T) {
      return (A / (kw * T)) * (t - k * T);
    } else {
      return (-A / (T * (1 - kw))) * (t - k * T) + A / (1 - kw);
    }
  }
}
