package com.adach.signalgen.generator.impl;

import com.adach.signalgen.generator.SignalGenerator;
import com.adach.signalgen.parameters.SignalParameters;
import java.util.concurrent.ThreadLocalRandom;

public class GaussianNoise extends SignalGenerator {

  public GaussianNoise() {
    this.discrete = false;
  }

  @Override
  protected Double calculateRealValue(SignalParameters params, double t, int sampleNumber) {
    double A = params.get("A");
    return ThreadLocalRandom.current().nextGaussian() * A;
  }
}
