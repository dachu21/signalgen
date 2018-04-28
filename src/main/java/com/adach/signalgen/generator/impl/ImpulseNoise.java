package com.adach.signalgen.generator.impl;

import com.adach.signalgen.generator.SignalGenerator;
import com.adach.signalgen.parameters.SignalParameters;

public class ImpulseNoise extends SignalGenerator {

  public ImpulseNoise() {
    this.discrete = true;
  }

  @Override
  protected Double calculateRealValue(SignalParameters params, double t, int sampleNumber) {
    double A = params.get("A");
    double p = params.get("p");
    if (p == 1 || Math.random() <= p) {
      return A;
    } else {
      return 0D;
    }
  }
}
