package com.adach.signalgen.generator.impl;

import com.adach.signalgen.generator.SignalGenerator;
import com.adach.signalgen.parameters.SignalParameters;

public class KroneckerDelta extends SignalGenerator {

  public KroneckerDelta() {
    this.discrete = true;
  }

  @Override
  protected Double calculateRealValue(SignalParameters params, double t, int sampleNumber) {
    if (sampleNumber == params.get("ns")) {
      return params.get("A");
    } else {
      return 0D;
    }
  }
}
