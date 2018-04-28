package com.adach.signalgen.calculator;

import com.adach.signalgen.signal.impl.RealSignal;

public class RealSignalCalculator extends SignalCalculator<RealSignal> {

  @Override
  public double average(RealSignal signal) {
    double sum = 0;
    for (Double value : signal.getRealValues()) {
      sum += value;
    }
    return 1.0 / signal.getRealValues().size() * sum;
  }

  @Override
  public double magnitude(RealSignal signal) {
    double sum = 0;
    for (Double value : signal.getRealValues()) {
      sum += Math.abs(value);
    }
    return 1.0 / signal.getRealValues().size() * sum;
  }

  @Override
  public double power(RealSignal signal) {
    double sum = 0;
    for (Double value : signal.getRealValues()) {
      sum += value * value;
    }
    return 1.0 / signal.getRealValues().size() * sum;
  }

  @Override
  public double variance(RealSignal signal) {
    double average = average(signal);
    double sum = 0;
    for (Double value : signal.getRealValues()) {
      sum += (value - average) * (value - average);
    }
    return 1.0 / signal.getRealValues().size() * sum;
  }

  @Override
  public double effectiveValue(RealSignal signal) {
    return Math.sqrt(power(signal));
  }
}
