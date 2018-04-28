package com.adach.signalgen.generator.impl;

import com.adach.signalgen.generator.SignalGenerator;
import com.adach.signalgen.parameters.SignalParameters;

public class PulseWave extends SignalGenerator {

  public PulseWave() {
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
      return A;
    } else {
      return 0D;
    }
  }
}
