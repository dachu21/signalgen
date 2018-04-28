package com.adach.signalgen.calculator;

import com.adach.signalgen.signal.Signal;

public abstract class SignalCalculator<T extends Signal> {

  public abstract double average(T signal);

  public abstract double magnitude(T signal);

  public abstract double power(T signal);

  public abstract double variance(T signal);

  public abstract double effectiveValue(T signal);
}
