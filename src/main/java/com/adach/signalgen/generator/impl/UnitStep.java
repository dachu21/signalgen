package com.adach.signalgen.generator.impl;

import com.adach.signalgen.generator.SignalGenerator;
import com.adach.signalgen.parameters.SignalParameters;

public class UnitStep extends SignalGenerator {

  public UnitStep() {
    this.discrete = false;
  }

  @Override
  protected Double calculateRealValue(SignalParameters params, double t, int sampleNumber) {
    double ts = params.get("ts");
    double A = params.get("A");
    if (t > ts) {
      return A;
    } else if (t == ts) {
      return A / 2;
    } else {
      return 0D;
    }
  }
}
