package com.adach.signalgen.generator.impl;

import com.adach.signalgen.generator.SignalGenerator;
import com.adach.signalgen.parameters.SignalParameters;

public class FullWaveRectifiedSine extends SignalGenerator {

  public FullWaveRectifiedSine() {
    this.discrete = false;
  }

  @Override
  protected Double calculateRealValue(SignalParameters params, double t, int sampleNumber) {
    this.period = params.get("T");

    double A = params.get("A");
    double T = params.get("T");
    double t1 = params.get("t1");
    return A * Math.abs(Math.sin(((2 * Math.PI) / T) * (t - t1)));
  }
}
